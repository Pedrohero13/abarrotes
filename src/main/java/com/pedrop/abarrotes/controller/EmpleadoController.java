/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.controller;

import com.pedrop.abarrotes.repository.EmpleadoRepository;
import com.pedrop.abarrotes.exeptions.EmpleadoNotFoundException;
import com.pedrop.abarrotes.model.Empleado;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TI - DEVTRAIN
 */
@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository repository;

    private final EmpleadoModelAssembler assembler;

    public EmpleadoController(EmpleadoModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/proveedor")
    public List<EntityModel<Empleado>> all() {
//        List<EntityModel<Empleado>> proveedores = repository.findAll().stream()
//                .map(assembler::toModel).collect(Collectors.toList());
//        return CollectionModel.of(proveedores, WebMvcLinkBuilder.linkTo(EmpleadoController.class).slash("/proveedor").withSelfRel());
        return repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
    }

    @PostMapping("/proveedor")
    public ResponseEntity<?> newEmpleado(@RequestBody Empleado newEmpleado) {
        EntityModel<Empleado> entityModel = assembler.toModel(repository.save(newEmpleado));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/proveedor/{id}")
    public EntityModel<Empleado> one(@PathVariable Long id) {

        Empleado proveedor = repository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id, ""));
        // Crear enlaces
        return assembler.toModel(proveedor);
    }

    @PutMapping("/proveedor/{id}")
    public ResponseEntity<?> remplaceEmpleado(@RequestBody Empleado newEmpleado, @PathVariable Long id) {
        Empleado updateEmployee = repository.findById(id).map(proveedor -> {
            proveedor.setNombre(newEmpleado.getNombre());
            proveedor.setDireccion(newEmpleado.getDireccion());
            proveedor.setEmail(newEmpleado.getEmail());
            proveedor.setTelefono(newEmpleado.getTelefono());
            proveedor.setActivo(newEmpleado.isActivo());
            proveedor.setApellido_paterno(newEmpleado.getApellido_paterno());
            proveedor.setApellido_materno(newEmpleado.getApellido_materno());
            proveedor.setContrasena(proveedor.getContrasena());
            return repository.save(proveedor);
        }).orElseThrow(() -> new EmpleadoNotFoundException(id, newEmpleado.getNombre()));

        EntityModel<Empleado> entityModel = assembler.toModel(repository.save(updateEmployee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
        
        Empleado encontrado = repository.findById(id).orElseThrow(() -> new EmpleadoNotFoundException(id, ""));
        if (Objects.nonNull(encontrado)) {
            repository.deleteById(id);
            EntityModel<Empleado> entityModel = assembler.toModel(encontrado);
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
            

        }
        return new ResponseEntity<>(encontrado, HttpStatus.OK);
//        }catch(Exception ex){
//            
//            response.put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.put("message", "Ocurrio un error al borrar");
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }

    }
}
