package com.sistema.electronico_backend.service;

import com.sistema.electronico_backend.entity.Product;
import com.sistema.electronico_backend.repository.postgres.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock // Mock del repositorio
    private ProductRepository productRepository;

    @InjectMocks
    private ProductoService productService;


//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void testCreateProduct() {
        Product  product = new Product(1L,"hecho en lima","Pan", BigDecimal.valueOf(10),50, LocalDateTime.now(),null);
        when(productRepository.save(product)).thenReturn(Mono.just(product));
        Mono<Product> result = productService.createProduct(product);

        StepVerifier.create(result)
                .expectNext(product)  // El producto debe ser lo mismo
                .verifyComplete();//se completa correctamente
    }

    @Test
    void testgetAllProducts() {
        Product product1 = new Product(1L, "hecho en lima", "Pan", BigDecimal.valueOf(10), 50, LocalDateTime.now(), null);
        Product product2 = new Product(2L, "hecho en peru", "Arroz", BigDecimal.valueOf(5), 100, LocalDateTime.now(), null);
        when(productRepository.findAll()).thenReturn(Flux.just(product1, product2));

        Flux<Product> result = productService.getAllProducts();

        StepVerifier.create(result)
                .expectNext(product1)
                .expectNext(product2)
                .verifyComplete();

    }
}
