package com.sandysystems.MongoDB.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.sandysystems.MongoDB.entity.Producto;
import com.sandysystems.MongoDB.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService servicio;

    public ProductoController(ProductoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Producto> listar() {
        return servicio.all();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtener(@PathVariable String id) {
        return servicio.get(id);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto p) {
        return servicio.save(p);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable String id, @RequestBody Producto p) {
        p.setId(id);
        return servicio.save(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        servicio.delete(id);
    }
}
