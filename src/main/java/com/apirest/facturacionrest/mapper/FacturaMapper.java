package com.apirest.facturacionrest.mapper;

import org.springframework.stereotype.Component;

import com.apirest.facturacionrest.dto.FacturaDTO;
import com.apirest.facturacionrest.model.Factura;

@Component
public class FacturaMapper {
    public FacturaDTO toDto(Factura factura) {
        if (factura == null) {
            throw new IllegalArgumentException("La FacturaEntity no puede ser nulo");
        }
        FacturaDTO facturaDto = new FacturaDTO();
        facturaDto.setId(factura.getId());
        facturaDto.setFecha(factura.getFecha());
        facturaDto.setTotalSinImpuestos(factura.getTotalSinImpuestos());
        facturaDto.setTotalConImpuestos(factura.getTotalConImpuestos());
        facturaDto.setClienteId(factura.getClienteId());
        return facturaDto;
    }
}
