/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedrop.abarrotes.exeptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author TI - DEVTRAIN
 */
@ControllerAdvice
public class EmpleadoNotFoudAdvice {
    @ResponseBody
    @ExceptionHandler(EmpleadoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> proveedorNotFoundHandler(EmpleadoNotFoundException ex) {
        Map<String,Object> response = new HashMap<>();
        response.put("status_code", HttpStatus.NOT_FOUND.value());
        response.put("message", ex.getMessage());
        return response;
    }
}
