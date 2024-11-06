package com.apirest.facturacionrest.service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.facturacionrest.dto.ClienteDTO;
import com.apirest.facturacionrest.model.Cliente;
import com.apirest.facturacionrest.respository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO guardarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        clienteRepository.save(cliente);
        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }

    public List<ClienteDTO> obtenerClientes() {
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getDireccion(),
                        cliente.getEmail(), cliente.getTelefono()))
                .collect(Collectors.toList());
    }

    public ClienteDTO obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getDireccion(), cliente.getEmail(),
                cliente.getTelefono());
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) throws Exception {
        Optional<Cliente> existingCliente = clienteRepository.findById(id);
        if (existingCliente.isPresent()) {
            Cliente cliente = existingCliente.get();

            cliente.setNombre(clienteDTO.getNombre());
            cliente.setDireccion(clienteDTO.getDireccion());
            cliente.setEmail(clienteDTO.getEmail());
            clienteRepository.save(cliente);
            return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getDireccion(), cliente.getEmail(),
                    cliente.getTelefono());
        } else {
            throw new Exception("Cliente no encontrado");
        }
    }
}