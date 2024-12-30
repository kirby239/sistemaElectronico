package com.sistema.electronico_backend.repository.mongo;

import com.sistema.electronico_backend.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    // Para buscar órdenes por cliente
    Flux<Order> findByCustomerId(Long customerId);
}
