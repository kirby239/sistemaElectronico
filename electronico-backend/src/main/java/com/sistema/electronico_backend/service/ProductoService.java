package com.sistema.electronico_backend.service;

import com.sistema.electronico_backend.entity.Product;
import com.sistema.electronico_backend.repository.postgres.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {
    private final ProductRepository productRepository;

    public ProductoService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
