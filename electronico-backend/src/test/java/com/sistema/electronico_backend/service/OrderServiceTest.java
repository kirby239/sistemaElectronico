package com.sistema.electronico_backend.service;

import com.sistema.electronico_backend.entity.Customer;
import com.sistema.electronico_backend.entity.Order;
import com.sistema.electronico_backend.entity.OrderItem;
import com.sistema.electronico_backend.entity.Product;
import com.sistema.electronico_backend.repository.mongo.OrderRepository;
import com.sistema.electronico_backend.repository.postgres.CustomerRepository;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
    void testCreateOrder() {
        // 1. Datos mínimos necesarios
        Customer customer = new Customer();
        customer.setId(1L);

        Product product = new Product(1L, "hecho en lima", "Pan",
                BigDecimal.valueOf(10), 50, LocalDateTime.now(), null);

        OrderItem item = new OrderItem();
        item.setProductId(1L);
        item.setQuantity(2);

        Order order = new Order();
        order.setCustomerId(1L);
        order.setItems(List.of(item));

        // 2. Mock de las respuestas básicas
        when(customerRepository.findById(1L)).thenReturn(Mono.just(customer));
        when(productRepository.findById(1L)).thenReturn(Mono.just(product));
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));
        when(orderRepository.save(any(Order.class))).thenReturn(Mono.just(order));

        // 3. Ejecutar
        Mono<Order> result = orderService.createOrder(order);

        // 4. Verificar solo lo esencial
        StepVerifier.create(result)
                .expectNextMatches(savedOrder ->
                        savedOrder.getStatus() == Order.OrderStatus.PENDING &&
                                savedOrder.getCreatedAt() != null &&
                                !savedOrder.getItems().isEmpty()
                )
                .verifyComplete();
    }
    @Test
    void testGetAllOrders() {
        // Preparar datos de prueba
        Order order1 = new Order();
        order1.setId("1");
        Order order2 = new Order();
        order2.setId("2");

        // Configurar mock
        when(orderRepository.findAll()).thenReturn(Flux.just(order1, order2));

        // Ejecutar y verificar
        StepVerifier.create(orderService.getAllOrders())
                .expectNext(order1)
                .expectNext(order2)
                .verifyComplete();
    }

    @Test
    void testGetOrderById() {
        // Preparar datos de prueba
        Order order = new Order();
        order.setId("1");

        // Configurar mock
        when(orderRepository.findById("1")).thenReturn(Mono.just(order));

        // Ejecutar y verificar
        StepVerifier.create(orderService.getOrderById("1"))
                .expectNext(order)
                .verifyComplete();
    }


    @Test
    void testGetOrdersByCustomer() {
        // Preparar datos de prueba
        Order order1 = new Order();
        order1.setCustomerId(1L);
        Order order2 = new Order();
        order2.setCustomerId(1L);

        when(orderRepository.findByCustomerId(1L)).thenReturn(Flux.just(order1, order2));

        StepVerifier.create(orderService.getOrdersByCustomer(1L))
                .expectNext(order1)
                .expectNext(order2)
                .verifyComplete();
    }

    @Test
    void testDeleteOrder() {
        // Preparar datos de prueba
        Order order = new Order();
        order.setId("1");

        OrderItem item = new OrderItem();
        item.setProductId(1L);
        item.setQuantity(2);
        item.setSubtotal(BigDecimal.valueOf(20));
        order.setItems(List.of(item));
        //Product product = new Product();
        Product product = new Product(1L, "hecho en lima", "Pan", BigDecimal.valueOf(10), 10, LocalDateTime.now(), null);

        //product.setId(1L);
        //product.setStock(10);

        // Configurar mocks
        when(orderRepository.findById("1")).thenReturn(Mono.just(order));
        when(productRepository.findById(1L)).thenReturn(Mono.just(product));
        when(productRepository.save(product)).thenReturn(Mono.just(product));
        when(orderRepository.deleteById("1")).thenReturn(Mono.empty());

        // Ejecutar y verificar
        StepVerifier.create(orderService.deleteOrder("1"))
                .verifyComplete();
    }

}
