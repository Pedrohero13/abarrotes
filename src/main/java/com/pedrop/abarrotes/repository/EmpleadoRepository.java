/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.repository;

import com.pedrop.abarrotes.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author TI - DEVTRAIN
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
}
