/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.controller;

import com.pedrop.abarrotes.repository.ProveedorRepository;
import com.pedrop.abarrotes.exeptions.ProveedorNotFoundException;
import com.pedrop.abarrotes.model.Proveedor;
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
public class ProveedorController {

    @Autowired
    private ProveedorRepository repository;

    private final ProveedorModelAssembler assembler;

    public ProveedorController(ProveedorModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/proveedor")
    public List<EntityModel<Proveedor>> all() {
//        List<EntityModel<Proveedor>> proveedores = repository.findAll().stream()
//                .map(assembler::toModel).collect(Collectors.toList());
//        return CollectionModel.of(proveedores, WebMvcLinkBuilder.linkTo(ProveedorController.class).slash("/proveedor").withSelfRel());
        return repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
    }

    @PostMapping("/proveedor")
    public ResponseEntity<?> newProveedor(@RequestBody Proveedor newProveedor) {
        EntityModel<Proveedor> entityModel = assembler.toModel(repository.save(newProveedor));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/proveedor/{id}")
    public EntityModel<Proveedor> one(@PathVariable Long id) {

        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id, ""));
        // Crear enlaces
        return assembler.toModel(proveedor);
    }

    @PutMapping("/proveedor/{id}")
    public ResponseEntity<?> remplaceProveedor(@RequestBody Proveedor newProveedor, @PathVariable Long id) {
        Proveedor updateEmployee = repository.findById(id).map(proveedor -> {
            proveedor.setNombre(newProveedor.getNombre());
            proveedor.setDireccion(newProveedor.getDireccion());
            proveedor.setCorreoElectronico(newProveedor.getCorreoElectronico());
            proveedor.setTelefono(newProveedor.getTelefono());
            return repository.save(proveedor);
        }).orElseThrow(() -> new ProveedorNotFoundException(id, newProveedor.getNombre()));

        EntityModel<Proveedor> entityModel = assembler.toModel(repository.save(updateEmployee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable Long id) {
        
        Proveedor encontrado = repository.findById(id).orElseThrow(() -> new ProveedorNotFoundException(id, ""));
        if (Objects.nonNull(encontrado)) {
            repository.deleteById(id);
            EntityModel<Proveedor> entityModel = assembler.toModel(encontrado);
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
