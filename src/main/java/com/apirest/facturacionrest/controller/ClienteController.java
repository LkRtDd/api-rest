package com.apirest.facturacionrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.facturacionrest.dto.ClienteDTO;
import com.apirest.facturacionrest.service.ClienteService;
import com.apirest.facturacionrest.utils.ApiResponseMsg;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    @Operation(summary = "Obtener todos los clientes", description = "Obtener todos los clientes de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados", content = @Content(schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error al buscar clientes", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "404", description = "Clientes no encontrados", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<?> getAllClientes() {
        try {
            List<ClienteDTO> clientes = clienteService.obtenerClientes();
            return ResponseEntity.ok(new ApiResponseMsg("Clientes encontrados", clientes));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al buscar clientes", e.getMessage()));
        }
    }

    @PostMapping("/crearcliente")
    @Operation(summary = "Crear un cliente", description = "Crear un cliente en la base de datos, se debe quitar el campo id del body ya que sera autogenerado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente creado", content = @Content(schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error al crear cliente", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<?> createClient(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.guardarCliente(clienteDTO);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente creado", clienteDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al crear cliente", e.getMessage()));
        }
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Obtener un cliente por id", description = "Obtener un cliente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error al buscar cliente", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            ClienteDTO cliente = clienteService.obtenerClientePorId(id);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente encontrado", cliente));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al buscar cliente", e.getMessage()));
        }
    }

    @DeleteMapping("/cliente/{id}")
    @Operation(summary = "Eliminar un cliente por id", description = "Eliminar un cliente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "400", description = "Error al eliminar cliente", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<?> deleteClientById(@PathVariable Long id) {
        try {
            clienteService.eliminarCliente(id);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente eliminado", id));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al eliminar cliente", e.getMessage()));
        }
    }

    @PutMapping("cliente/{id}")
    @Operation(summary = "Actualizar un cliente por id", description = "Actualizar un cliente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado", content = @Content(schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error al actualizar cliente", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO updatedCliente = clienteService.actualizarCliente(id, clienteDTO);
            return ResponseEntity.ok(new ApiResponseMsg("Cliente actualizado", updatedCliente));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error al actualizar cliente", e.getMessage()));
        }
    }
}
