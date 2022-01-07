package com.myshop.product.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Product code is mandatory!")
    @Column(name = "product_code", unique = true)
    private Long productCode;

    @NotBlank(message = "Name is mandatory and must not be blank!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description is mandatory and must nob be blank!")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price cannot be negative or zero!")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull(message = "Quantity is mandatory")
    @PositiveOrZero(message = "Quantity must be positive or zero")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Product must have status (ONLINE|OFFLINE|DISCONTINUED)!")
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    public Product() {
    }

    public Product(Long productCode, String name, String description, BigDecimal price, Integer quantity, ProductStatus status) {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
