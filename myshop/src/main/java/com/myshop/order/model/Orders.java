package com.myshop.order.model;

import com.myshop.order.model.enums.OrderStatus;
import com.myshop.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "TotalAmount cannot be negative or zero!")
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @NotNull(message = "User is mandatory")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @NotNull(message = "Orders status cannot be empty!")
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.REMOVE)
    private List<OrderEntry> orderEntries;

    public Orders() {
    }

    public Orders(User user, OrderStatus orderStatus) {
        this.user = user;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", user=" + user +
                ", orderStatus=" + orderStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) && Objects.equals(totalAmount, orders.totalAmount)
                && Objects.equals(user, orders.user) && orderStatus == orders.orderStatus
                && Objects.equals(orderEntries, orders.orderEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalAmount, user, orderStatus, orderEntries);
    }
}
