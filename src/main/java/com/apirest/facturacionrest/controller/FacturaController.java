package com.apirest.facturacionrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.facturacionrest.dto.FacturaDTO;
import com.apirest.facturacionrest.service.FacturaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaService  facturaService;

    @PostMapping
    public ResponseEntity<FacturaDTO> generarFactura(@RequestBody FacturaDTO facturaDTO){
        return new ResponseEntity<>(facturaService.generarFactura(facturaDTO), HttpStatus.CREATED);
    }
    
    
    
}
