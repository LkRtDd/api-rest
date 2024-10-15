package com.apirest.facturacionrest.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.facturacionrest.model.Producto;

public interface ProductoRepository extends JpaRepository <Producto, Long> {

}
