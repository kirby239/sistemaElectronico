package com.sistema.electronico_backend.service;

import com.sistema.electronico_backend.entity.Customer;
import com.sistema.electronico_backend.repository.postgres.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> createCustomer(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    // Obtener todos los clientes
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
