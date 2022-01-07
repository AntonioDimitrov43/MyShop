package com.myshop.cart.model;

import com.myshop.product.model.Product;
import com.myshop.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "cart")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Product is mandatory!")
    @ManyToOne(targetEntity = Product.class, cascade = CascadeType.REFRESH)
    private Product product;

    @NotNull(message = "User is mandatory")
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REMOVE)
    private User user;

    @NotNull(message = "Quantity is mandatory")
    @Positive(message = "Quantity cannot be negative or zero")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(Product product, User user, Integer quantity) {
        this.product = product;
        this.user = user;
        this.quantity = quantity;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
