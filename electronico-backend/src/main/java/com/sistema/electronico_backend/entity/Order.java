package com.sistema.electronico_backend.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    // Referencia al cliente
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long customerId;

    // Lista de productos en el pedido
    @NotEmpty(message = "La orden debe tener al menos un item")
    private List<OrderItem> items;

    @NotNull(message = "El total es obligatorio")
    private BigDecimal total;

    private LocalDateTime createdAt;

    @NotNull(message = "El estado es obligatorio")
    private OrderStatus status;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public @NotNull(message = "El ID del cliente es obligatorio") Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@NotNull(message = "El ID del cliente es obligatorio") Long customerId) {
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotEmpty(message = "La orden debe tener al menos un item") List<OrderItem> getItems() {
        return items;
    }

    public void setItems(@NotEmpty(message = "La orden debe tener al menos un item") List<OrderItem> items) {
        this.items = items;
    }

    public @NotNull(message = "El estado es obligatorio") OrderStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "El estado es obligatorio") OrderStatus status) {
        this.status = status;
    }

    public @NotNull(message = "El total es obligatorio") BigDecimal getTotal() {
        return total;
    }

    public void setTotal(@NotNull(message = "El total es obligatorio") BigDecimal total) {
        this.total = total;
    }

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        CANCELLED
    }

}
