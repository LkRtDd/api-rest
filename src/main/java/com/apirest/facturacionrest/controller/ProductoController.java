package com.apirest.facturacionrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apirest.facturacionrest.model.Producto;
import com.apirest.facturacionrest.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        Producto productoActualizado = productoService.updateProducto(id, detallesProducto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.ok("Producto eliminado: " + id);
    }
}