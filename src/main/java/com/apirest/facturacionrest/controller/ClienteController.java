package com.apirest.facturacionrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.facturacionrest.dto.ClienteDTO;
import com.apirest.facturacionrest.service.ClienteService;
import com.apirest.facturacionrest.utils.ApiResponseMsg;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllClientes() {
        try {
            List<ClienteDTO> clientes = clienteService.obtenerClientes();
            return ResponseEntity.ok(new ApiResponseMsg("Clientes encontrados", clientes));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al buscar clientes", e.getMessage()));
        }
    }

}
