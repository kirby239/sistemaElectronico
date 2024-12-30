package com.sistema.electronico_backend.service;

import com.sistema.electronico_backend.entity.Customer;
import com.sistema.electronico_backend.entity.Product;
import com.sistema.electronico_backend.repository.postgres.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock // Mock del repositorio
    private CustomerRepository customerRepository;

    @InjectMocks
    private  CustomerService customerService;

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer(1L,"kirby@gmail.com","kirby",LocalDateTime.now(),null);
        when(customerRepository.save(customer)).thenReturn(Mono.just(customer));
        Mono<Customer> result = customerService.createCustomer(customer);

        StepVerifier.create(result)
                .expectNext(customer)  // El producto debe ser lo mismo
                .verifyComplete();//se completa correctamente
    }

    @Test
    void testgetAllCustomers() {
        Customer customer1 = new Customer(1L,"kirby@gmail.com","kirby",LocalDateTime.now(),null);
        Customer customer2 = new Customer(1L,"mayer@gmail.com","mayer",LocalDateTime.now(),null);
        when(customerRepository.findAll()).thenReturn(Flux.just(customer1, customer2));

        Flux<Customer> result = customerService.getAllCustomers();

        StepVerifier.create(result)
                .expectNext(customer1)
                .expectNext(customer2)
                .verifyComplete();

    }
}
