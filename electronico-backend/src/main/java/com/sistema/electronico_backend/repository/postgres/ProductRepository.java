package com.sistema.electronico_backend.repository.postgres;

import com.sistema.electronico_backend.entity.Product;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {
    @Query("UPDATE products SET stock = stock - :quantity WHERE id = :id AND stock >= :quantity")
    Mono<Boolean> updateStock(Long id, Integer quantity);
}
