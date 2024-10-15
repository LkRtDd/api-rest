package com.apirest.facturacionrest.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.facturacionrest.model.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

}
