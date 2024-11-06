package com.apirest.facturacionrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apirest.facturacionrest.dto.FacturaDTO;
import com.apirest.facturacionrest.service.FacturaService;
import com.apirest.facturacionrest.utils.ApiResponseMsg;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping
    @Operation(summary = "Crear una factura", description = "Crear una factura en la base de datos, se debe agregar el parametro clienteId en la url(ejemplo: /facturas?clienteId=1)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura creada", content = @Content(schema = @Schema(implementation = FacturaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error al crear factura", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<FacturaDTO> createFactura(@RequestParam Long clienteId, @RequestBody FacturaDTO facturaDTO) {
        FacturaDTO createdFactura = facturaService.generarFactura(clienteId, facturaDTO);
        return new ResponseEntity<>(createdFactura, HttpStatus.CREATED);
    }
}
