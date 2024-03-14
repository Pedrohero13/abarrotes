/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.controller;

import com.pedrop.abarrotes.model.Empleado;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author TI - DEVTRAIN
 */
@Component
public class EmpleadoModelAssembler implements RepresentationModelAssembler<Empleado, EntityModel<Empleado>>{

    @Override
    public EntityModel<Empleado> toModel(Empleado entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(EmpleadoController.class).slash("/proveedor/"+entity.getId()).withSelfRel(), 
                WebMvcLinkBuilder.linkTo(EmpleadoController.class).slash("/proveedor").withRel("all"));
    }
    
}
