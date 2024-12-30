package com.sistema.electronico_backend.dto;

import jakarta.validation.Valid;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Valid
public class OrderDto {
    private String id;
    private CustomerDTO customer;
    private List<OrderItemDTO> items;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private String status;

    @Data
    public static class CustomerDTO {
        private Long id;
        private String name;
        private String email;
    }

    @Data
    public static class OrderItemDTO {
        private Long productId;
        private String productName;
        private String productDescription;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal subtotal;
    }
}
