package com.apirest.facturacionrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apirest.facturacionrest.dto.FacturaDTO;
import com.apirest.facturacionrest.service.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<FacturaDTO> createFactura(@RequestParam Long clienteId, @RequestBody FacturaDTO facturaDTO) {
        FacturaDTO createdFactura = facturaService.generarFactura(clienteId, facturaDTO);
        return new ResponseEntity<>(createdFactura, HttpStatus.CREATED);
    }
}
