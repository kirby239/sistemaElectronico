package com.sistema.electronico_backend.repository.postgres;

import com.sistema.electronico_backend.entity.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
    Mono<Customer> findByEmail(String email);
}
