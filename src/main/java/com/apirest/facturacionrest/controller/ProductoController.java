package com.apirest.facturacionrest.controller;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apirest.facturacionrest.model.Producto;
import com.apirest.facturacionrest.service.ProductoService;
import com.apirest.facturacionrest.utils.ApiResponseMsg;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    // @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Obtener todos los productos de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Error al buscar productos", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "404", description = "Productos no encontrados", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por ID", description = "Obtener un producto de la base de datos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Error al buscar producto", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    @Operation(summary = "Crear un producto", description = "Crear un producto en la base de datos, se debe quitar el campo id del body ya que sera autogenerado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Error al crear producto", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualizar un producto en la base de datos el campo id va en el endpoint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Error al actualizar producto", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        Producto productoActualizado = productoService.updateProducto(id, detallesProducto);
        return ResponseEntity.ok(new ApiResponseMsg("Producto Actualizado", productoActualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Eliminar un producto de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "400", description = "Error al eliminar producto", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class)))
    })
    public ResponseEntity<ApiResponseMsg> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.ok(new ApiResponseMsg("Producto eliminado", id));
    }
}