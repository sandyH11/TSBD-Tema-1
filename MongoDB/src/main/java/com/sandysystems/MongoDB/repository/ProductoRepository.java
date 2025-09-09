package com.sandysystems.MongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sandysystems.MongoDB.entity.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {
}