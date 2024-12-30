package com.sistema.electronico_backend.controller;

import com.sistema.electronico_backend.entity.Customer;
import com.sistema.electronico_backend.entity.Product;
import com.sistema.electronico_backend.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProducController {
    private  final ProductoService productoService;

    public ProducController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productoService.createProduct(product);
    }

    // Obtener todos los clientes
    @GetMapping("/list")
    public Flux<Product> getAllProduct() {
        return productoService.getAllProducts();
    }

}
