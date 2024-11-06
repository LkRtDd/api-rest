package com.apirest.facturacionrest.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.facturacionrest.dto.DetalleFacturaDTO;
import com.apirest.facturacionrest.dto.FacturaDTO;
import com.apirest.facturacionrest.model.Cliente;
import com.apirest.facturacionrest.model.DetalleFactura;
import com.apirest.facturacionrest.model.Factura;
import com.apirest.facturacionrest.model.Producto;
import com.apirest.facturacionrest.respository.ClienteRepository;
import com.apirest.facturacionrest.respository.FacturaRepository;
import com.apirest.facturacionrest.respository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public FacturaDTO generarFactura(Long clienteId, FacturaDTO facturaDTO) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));

        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFecha(new Date());

        List<DetalleFactura> detalles = new ArrayList<>();
        List<DetalleFacturaDTO> detalleFacturaDTOs = new ArrayList<>();
        Double totalSinImpuestos = 0.0;
        Double totalImpuestos = 0.0;

        for (DetalleFacturaDTO detalleDTO : facturaDTO.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto not found"));

            DetalleFactura detalle = new DetalleFactura();
            detalle.setProducto(producto);
            detalle.setFactura(factura);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            Double subTotal = detalleDTO.getCantidad() * producto.getPrecio();
            Double impuesto = subTotal * producto.getImpuesto();
            detalle.setSubTotal(subTotal);
            detalles.add(detalle);

            DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO(clienteId, null, impuesto);
            detalleFacturaDTO.setProductoId(producto.getId());
            detalleFacturaDTO.setCantidad(detalleDTO.getCantidad());
            detalleFacturaDTO.setSubTotal(subTotal);
            detalleFacturaDTOs.add(detalleFacturaDTO);

            totalSinImpuestos += subTotal;
            totalImpuestos += impuesto;
        }

        factura.setDetalleFactura(detalles);
        factura.setTotalSinImpuestos(totalSinImpuestos);
        factura.setTotalImpuestos(totalImpuestos);
        factura.setTotalConImpuestos(totalSinImpuestos + totalImpuestos);

        facturaRepository.save(factura);

        FacturaDTO resultDTO = new FacturaDTO();
        resultDTO.setId(factura.getId());
        resultDTO.setClienteId(clienteId);
        resultDTO.setDetalles(detalleFacturaDTOs);
        resultDTO.setTotalSinImpuestos(totalSinImpuestos);
        resultDTO.setTotalImpuestos(totalImpuestos);
        resultDTO.setTotalConImpuestos(totalSinImpuestos + totalImpuestos);
        resultDTO.setFecha(factura.getFecha());

        return resultDTO;
    }
}