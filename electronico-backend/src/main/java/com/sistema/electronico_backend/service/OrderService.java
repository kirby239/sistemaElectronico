package com.sistema.electronico_backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sistema.electronico_backend.entity.Order;
import com.sistema.electronico_backend.entity.OrderItem;
import com.sistema.electronico_backend.repository.postgres.CustomerRepository;
import com.sistema.electronico_backend.repository.mongo.OrderRepository;
import com.sistema.electronico_backend.repository.postgres.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Mono<Order> createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);

        return customerRepository.findById(order.getCustomerId())
                .switchIfEmpty(Mono.error(new RuntimeException("Cliente no encontrado")))
                .flatMap(customer -> {

                    return Flux.fromIterable(order.getItems())
                            .flatMap(item -> productRepository.findById(item.getProductId())
                                    .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado: " + item.getProductId())))
                                    .flatMap(product -> {
                                        if (product.getStock() < item.getQuantity()) {
                                            return Mono.error(new RuntimeException("Stock insuficiente para el producto: " + product.getName()));
                                        }
                                        // Actualizar stock
                                        product.setStock(product.getStock() - item.getQuantity());
                                        return productRepository.save(product)
                                                .then(Mono.just(product))
                                                .map(updatedProduct -> {
                                                    //  item con datos del producto
                                                    item.setPrice(updatedProduct.getPrice());
                                                    item.setSubtotal(updatedProduct.getPrice().multiply(new BigDecimal(item.getQuantity())));
                                                    return item;
                                                });
                                    }))
                            .collectList()
                            .map(items -> {
                                order.setItems(items);
                                // Calcular total
                                BigDecimal total = items.stream()
                                        .map(OrderItem::getSubtotal)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                order.setTotal(total);
                                return order;
                            })
                            .flatMap(orderRepository::save);
                });
    }

    public Mono<Order> updateOrder(String id, Order order) {
        return orderRepository.findById(id)
                .flatMap(existingOrder -> {
                    // La fecha de creación y actualizar solo los detalles
                    order.setId(id);
                    order.setCreatedAt(existingOrder.getCreatedAt());

                    // Actualizar el stock de los productos
                    return Flux.fromIterable(existingOrder.getItems())
                            .flatMap(item -> productRepository.findById(item.getProductId())
                                    .flatMap(product -> {
                                        if (product != null) {
                                            product.setStock(product.getStock() + item.getQuantity());  // Restaurar el stock
                                            return productRepository.save(product);
                                        }
                                        return Mono.error(new RuntimeException("Producto no encontrado: " + item.getProductId()));
                                    }))
                            .then(createOrder(order));  // Crear la orden actualizada
                });
    }

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Mono<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Flux<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Mono<Void> deleteOrder(String id) {
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Orden no encontrada con ID: " + id)))
                .flatMap(order -> {
                    return Flux.fromIterable(order.getItems())
                            .flatMap(item -> productRepository.findById(item.getProductId())
                                    // Si no se encuentra el producto
                                    .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado: " + item.getProductId())))
                                    .flatMap(product -> {
                                        // Restaurar el stock en el producto
                                        product.setStock(product.getStock() + item.getQuantity());
                                        return productRepository.save(product);
                                    }))
                            .then(orderRepository.deleteById(id));
                });
    }

}
