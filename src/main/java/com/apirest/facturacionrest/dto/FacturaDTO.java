package com.apirest.facturacionrest.dto;

import java.time.LocalDate;
import java.util.List;

public class FacturaDTO {
    private Long id;
    private Long clienteId;
    private List<DetalleFacturaDTO> detalles;
    private Double totalSinImpuestos;
    private Double totalImpuestos;
    private Double totalConImpuestos;
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DetalleFacturaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFacturaDTO> detalles) {
        this.detalles = detalles;
    }

    public Double getTotalSinImpuestos() {
        return totalSinImpuestos;
    }

    public void setTotalSinImpuestos(Double totalSinImpuestos) {
        this.totalSinImpuestos = totalSinImpuestos;
    }

    public Double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(Double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public Double getTotalConImpuestos() {
        return totalConImpuestos;
    }

    public void setTotalConImpuestos(Double totalConImpuestos) {
        this.totalConImpuestos = totalConImpuestos;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
