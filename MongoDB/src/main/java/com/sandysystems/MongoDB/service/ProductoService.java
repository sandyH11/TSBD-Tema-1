package com.sandysystems.MongoDB.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.sandysystems.MongoDB.entity.Producto;
import com.sandysystems.MongoDB.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> all() { return repo.findAll(); }

    public Optional<Producto> get(String id) { return repo.findById(id); }

    public Producto save(Producto p) { return repo.save(p); }

    public void delete(String id) { repo.deleteById(id); }
}
