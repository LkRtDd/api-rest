package com.apirest.facturacionrest.dto;

public class DetalleFacturaDTO {
    private Long productoId;
    private Integer cantidad;
    private Double subTotal;

    public DetalleFacturaDTO(Long productoId, Integer cantidad, Double subTotal) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

}
