package com.apirest.facturacionrest.mapper;

import org.springframework.stereotype.Component;

import com.apirest.facturacionrest.dto.ClienteDTO;
import com.apirest.facturacionrest.model.Cliente;

@Component
public class ClienteMapper {

    public ClienteDTO toDto(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El ClienteEntity no puede ser nulo");
        }
        ClienteDTO clienteDto = new ClienteDTO(null, null, null, null, null);
        clienteDto.setId(cliente.getId());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setDireccion(cliente.getDireccion());
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setTelefono(cliente.getTelefono());
        return clienteDto;
    }

    public Cliente toEntity(ClienteDTO clienteDto) {
        if (clienteDto == null) {
            throw new IllegalArgumentException("El ClienteDTO no puede ser nulo");
        }
        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefono(clienteDto.getTelefono());
        return cliente;
    }
}
