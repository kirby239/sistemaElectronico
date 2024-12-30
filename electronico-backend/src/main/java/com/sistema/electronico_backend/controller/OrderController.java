package com.sistema.electronico_backend.controller;

import com.sistema.electronico_backend.entity.Order;
import com.sistema.electronico_backend.service.OrderService;
import com.sistema.electronico_backend.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // CREAR PEDIDO
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> createOrder(@Valid @RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // ACTUALIZAR PEDIDO
    @PutMapping("/{id}")
    public Mono<Order> updateOrder(@PathVariable String id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    // ELIMINAR PEDIDO
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<String>>> deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id)
                .then(Mono.just(ResponseEntity.ok(new ApiResponse<String>(HttpStatus.OK.value(), "Orden eliminada correctamente", null))))
                .onErrorResume(RuntimeException.class, e -> Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<String>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null))
                ));
    }
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Mono<Void> deleteOrder(@PathVariable String id) {
//        return orderService.deleteOrder(id);
//    }

    // OBTENER TODOS LOS PEDIDOS
    @GetMapping("/list/order")
    public Flux<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // OBTENER PEDIDO POR ID
    @GetMapping("/{id}")
    public Mono<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    // OBTENER PEDIDOS POR CLIENTE
    @GetMapping("/customer/{customerId}")
    public Flux<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }

}
