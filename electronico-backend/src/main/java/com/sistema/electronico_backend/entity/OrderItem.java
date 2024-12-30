package com.sistema.electronico_backend.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private int quantity;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private BigDecimal price;

    private BigDecimal subtotal;
   // private String productName;


    public @NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio debe ser mayor o igual a 0") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio debe ser mayor o igual a 0") BigDecimal price) {
        this.price = price;
    }

    public @NotNull(message = "El ID del producto es obligatorio") Long getProductId() {
        return productId;
    }

    public void setProductId(@NotNull(message = "El ID del producto es obligatorio") Long productId) {
        this.productId = productId;
    }

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "La cantidad es obligatoria") @Min(value = 1, message = "La cantidad debe ser mayor a 0") int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
