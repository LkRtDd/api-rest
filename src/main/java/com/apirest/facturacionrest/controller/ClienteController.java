package com.apirest.facturacionrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.facturacionrest.dto.ClienteDTO;
import com.apirest.facturacionrest.service.ClienteService;
import com.apirest.facturacionrest.utils.ApiResponseMsg;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @PostMapping("/crearcliente")
    public ResponseEntity<?> createClient(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.guardarCliente(clienteDTO);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente creado", clienteDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al crear cliente", e.getMessage()));
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            ClienteDTO cliente = clienteService.obtenerClientePorId(id);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente encontrado", cliente));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al buscar cliente", e.getMessage()));
        }
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long id) {
        try {
            clienteService.eliminarCliente(id);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente eliminado", id));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al eliminar cliente", e.getMessage()));
        }
    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO updatedCliente = clienteService.actualizarCliente(id, clienteDTO);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente actualizado", updatedCliente));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al actualizar cliente", e.getMessage()));
        }
    }
}
