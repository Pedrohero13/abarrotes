/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.exeptions;

/**
 *
 * @author TI - DEVTRAIN
 */
public class EmpleadoNotFoundException extends RuntimeException {
    public EmpleadoNotFoundException(Long id, String name) {
        super("No se encontro el proveedor "+ name+" con el id: " + id +" ");
    }
}
