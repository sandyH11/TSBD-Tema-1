package com.sandysystems.MySQL.service;

import com.sandysystems.MySQL.entity.Producto;
import com.sandysystems.MySQL.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        return productoRepository.findById(id).map(existing -> {
            existing.setNombre(producto.getNombre());
            existing.setDescripcion(producto.getDescripcion());
            existing.setPrecio(producto.getPrecio());
            existing.setCantidad(producto.getCantidad());
            return productoRepository.save(existing);
        }).orElseGet(() -> {
            producto.setId(id);
            return productoRepository.save(producto);
        });
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}