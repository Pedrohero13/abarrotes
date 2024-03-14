/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.exeptions;

/**
 *
 * @author TI - DEVTRAIN
 */
public class ProveedorNotFoundException extends RuntimeException {

    public ProveedorNotFoundException(Long id, String name) {
        super("No se encontro el proveedor "+ name+" con el id: " + id +" ");
    }
}
