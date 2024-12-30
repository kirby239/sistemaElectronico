package com.sistema.electronico_backend.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Table(name = "products")
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column("name")
    private String name;

    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    @Column("price")
    private BigDecimal price;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    @Column("stock")
    private Integer stock;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    public Product(Long id, String name, String description, BigDecimal price, Integer stock, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "El nombre es obligatorio") String name) {
        this.name = name;
    }

    public @NotNull(message = "El stock es obligatorio") @Min(value = 0, message = "El stock debe ser mayor o igual a 0") Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull(message = "El stock es obligatorio") @Min(value = 0, message = "El stock debe ser mayor o igual a 0") Integer stock) {
        this.stock = stock;
    }

    public @NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio debe ser mayor o igual a 0") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "El precio es obligatorio") @Min(value = 0, message = "El precio debe ser mayor o igual a 0") BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
