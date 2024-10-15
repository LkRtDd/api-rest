package com.apirest.facturacionrest.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "facturas")
public class Factura {
    @Id
    private Long id;
    
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleFactura> detalleFactura;

    private Double totalSinImpuestos;
    private Double totalImpuestos;
    private Double totalConImpuestos;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<DetalleFactura> getDetalleFactura() {
        return detalleFactura;
    }
    public void setDetalleFactura(List<DetalleFactura> detalleFactura) {
        this.detalleFactura = detalleFactura;
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

    
}
