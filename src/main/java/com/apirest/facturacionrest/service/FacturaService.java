package com.apirest.facturacionrest.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.facturacionrest.dto.DetalleFacturaDTO;
import com.apirest.facturacionrest.dto.FacturaDTO;
import com.apirest.facturacionrest.model.DetalleFactura;
import com.apirest.facturacionrest.model.Factura;
import com.apirest.facturacionrest.model.Producto;
import com.apirest.facturacionrest.respository.FacturaRepository;
import com.apirest.facturacionrest.respository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public FacturaDTO generarFactura (FacturaDTO facturaDTO){
        Factura factura = new Factura();
        factura.setFecha(LocalDate.now());

        List<DetalleFactura> detalles = new ArrayList<>();
        Double totalSinImpuestos = 0.0;
        Double totalImpuestos = 0.0;

        for (DetalleFacturaDTO detalleDTO : facturaDTO.getDetalles()){
            Producto producto = productoRepository.findById(detalleDTO.getProductoId()).orElseThrow();
            Double subTotal = producto.getPrecio() * detalleDTO.getCantidad();
            Double impuesto = producto.getImpuesto() * subTotal;

            DetalleFactura detalle = new DetalleFactura();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubTotal(subTotal);
            detalles.add(detalle);

            totalSinImpuestos += subTotal;
            totalImpuestos += impuesto;
        }

        factura.setDetalleFactura(detalles);
        factura.setTotalSinImpuestos(totalSinImpuestos);
        factura.setTotalImpuestos(totalImpuestos);
        factura.setTotalConImpuestos(totalSinImpuestos + totalImpuestos);

        facturaRepository.save(factura);

        facturaDTO.setId(factura.getId());
        facturaDTO.setFecha(factura.getFecha());
        facturaDTO.setTotalSinImpuestos(factura.getTotalSinImpuestos());
        facturaDTO.setTotalImpuestos(factura.getTotalImpuestos());
        facturaDTO.setTotalConImpuestos(factura.getTotalConImpuestos());

        return facturaDTO;
    }
}
