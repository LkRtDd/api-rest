package com.apirest.facturacionrest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;
    private Double impuesto;
    private Double stock;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Double getImpuesto() {
        return impuesto;
    }
    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }
    public Double getStock() {
        return stock;
    }
    public void setStock(Double stock) {
        this.stock = stock;
    }

    
}
