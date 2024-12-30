package com.sistema.electronico_backend.controller;

import com.sistema.electronico_backend.entity.Customer;
import com.sistema.electronico_backend.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    // Obtener todos los clientes
    @GetMapping("/list")
    public Flux<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
