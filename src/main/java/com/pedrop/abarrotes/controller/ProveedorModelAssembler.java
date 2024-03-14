/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.controller;

import com.pedrop.abarrotes.controller.ProveedorController;
import com.pedrop.abarrotes.model.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author TI - DEVTRAIN
 */
@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>>{

    @Override
    public EntityModel<Proveedor> toModel(Proveedor entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(ProveedorController.class).slash("/proveedor/"+entity.getId()).withSelfRel(), 
                WebMvcLinkBuilder.linkTo(ProveedorController.class).slash("/proveedor").withRel("all"));
    }
    
}
