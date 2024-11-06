package com.apirest.facturacionrest.mapper;

import org.springframework.stereotype.Component;

import com.apirest.facturacionrest.dto.DetalleFacturaDTO;
import com.apirest.facturacionrest.model.DetalleFactura;

@Component
public class DetalleFacturaMapper {
    public DetalleFacturaDTO toDto(DetalleFactura detalleFactura) {
        if (detalleFactura == null) {
            throw new IllegalArgumentException("El DetalleFacturaEntity no puede ser nulo");
        }
        DetalleFacturaDTO detalleFacturaDto = new DetalleFacturaDTO(null, null, null);
        detalleFacturaDto.setCantidad(detalleFactura.getCantidad());
        detalleFacturaDto.setSubTotal(detalleFactura.getSubTotal());
        detalleFacturaDto.setProductoId(detalleFactura.getProductoId());
        return detalleFacturaDto;
    }

    public DetalleFactura toEntity(DetalleFacturaDTO detalleFacturaDto) {
        if (detalleFacturaDto == null) {
            throw new IllegalArgumentException("El DetalleFacturaDTO no puede ser nulo");
        }
        DetalleFactura detalleFactura = new DetalleFactura();
        detalleFactura.setCantidad(detalleFacturaDto.getCantidad());
        detalleFactura.setSubTotal(detalleFacturaDto.getSubTotal());
        detalleFactura.setProductoId();
        return detalleFactura;
    }
}
