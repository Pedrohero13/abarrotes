/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author TI - DEVTRAIN
 */
@Entity
@Data
public class Empleado {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String direccion;
    private String contrasena;
    private String telefono;
    private boolean activo;
    
    
    
}
