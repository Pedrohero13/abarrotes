/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.model;

import java.io.Serializable;
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
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String direccion;
    private Long telefono;
    private String correoElectronico;
    
    
}
