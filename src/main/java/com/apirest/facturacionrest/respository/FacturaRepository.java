package com.apirest.facturacionrest.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.facturacionrest.model.Factura;

public interface FacturaRepository extends JpaRepository <Factura, Long> {

}
