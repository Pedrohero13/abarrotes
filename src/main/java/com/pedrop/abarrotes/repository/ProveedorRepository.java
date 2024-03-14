/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pedrop.abarrotes.repository;

import com.pedrop.abarrotes.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author TI - DEVTRAIN
 */
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
    
}
