package com.myshop.order.model;

import com.myshop.product.model.Product;
import jdk.jfr.Enabled;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_entry")
public class OrderEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @NotNull(message = "Quantity is mandatory")
    @Positive(message = "Quantity cannot be negative or zero")
    private Integer quantity;

    @Positive(message = "TotalAmount cannotb be negative or zero!")
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @NotNull(message = "Product code is mandatory!")
    @Column(name = "order_id")
    @Positive(message = "Product code must be positive!")
    private Long orderId;

    public OrderEntry() {
    }

    public OrderEntry(Product product, Integer quantity, BigDecimal totalAmount, Long orderId) {
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderEntry{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", orderId=" + orderId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntry that = (OrderEntry) o;
        return Objects.equals(id, that.id) && Objects.equals(product, that.product)
                && Objects.equals(quantity, that.quantity) && Objects.equals(totalAmount, that.totalAmount)
                && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, totalAmount, orderId);
    }
}
