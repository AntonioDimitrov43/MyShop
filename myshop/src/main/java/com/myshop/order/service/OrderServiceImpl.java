package com.myshop.order.service;

import com.myshop.cart.model.CartItem;
import com.myshop.cart.service.CartItemServiceImpl;
import com.myshop.security.exceptions.BadIdException;
import com.myshop.security.exceptions.ElementNotFoundException;
import com.myshop.order.model.Orders;
import com.myshop.order.model.OrderEntry;
import com.myshop.order.model.enums.OrderStatus;
import com.myshop.order.repository.OrderRepository;
import com.myshop.order.service.interfaces.OrderService;
import com.myshop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemServiceImpl cartItemService;
    private final OrderEntryServiceImpl orderEntryService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                            CartItemServiceImpl cartItemService, OrderEntryServiceImpl orderEntryService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemService = cartItemService;
        this.orderEntryService = orderEntryService;
    }

    @Override
    public Orders createOrder(Orders orders) {
        if(orders == null){
            throw  new IllegalArgumentException("Orders cannot be null!");
        }
        if (!userRepository.existsByUsername(orders.getUser().getUsername())){
            throw new ElementNotFoundException("User with username = " + orders.getUser().getUsername() + " not found!");
        }
        List<CartItem> items = cartItemService.getCartForUser(orders.getUser().getUsername());

        if (items.isEmpty()){
            throw new IllegalArgumentException("User = " + orders.getUser().getUsername() +" cart is empty!");
        }

        orders.setUser(userRepository.getByUsername(orders.getUser().getUsername()));

        Long idOrder = orderRepository.save(orders).getId();

        items.forEach(item -> orderEntryService.saveOrderEntry(new OrderEntry(item.getProduct(), item.getQuantity(),
                BigDecimal.valueOf(item.getProduct().getPrice().doubleValue() * item.getQuantity()), idOrder)));

        cartItemService.moveToOrderEntryByUsername(orders.getUser().getUsername());

        Orders savedOrders = orderRepository.getById(idOrder);

        BigDecimal totalPrice = new BigDecimal(0);

        for (OrderEntry orderEntry: orderEntryService.getAllByOrder(idOrder)){
            totalPrice = totalPrice.add(orderEntry.getTotalAmount());
        }
        savedOrders.setTotalAmount(totalPrice);

        return orderRepository.save(savedOrders);

    }
    @Override
    public List<Orders> findAllForUser(String username) {
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username is mandatory and cannot be blank!");
        }

        if (!userRepository.existsByUsername(username)){
            throw new ElementNotFoundException("User with username = " + username + " not found!");
        }

        List<Orders> orders = orderRepository.findAllByUserUsername(username);
        orders.forEach(o -> o.getUser().setPassword(null));


        return orders.stream()
                .filter(o -> !o.getOrderStatus().equals(OrderStatus.SENT))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Orders> findByIdForUser(Long id, String username) {
        if(id == null || id <0){
            throw new BadIdException("ID cannot be negative or null!");
        }

        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username is mandatory and cannot be blank!");
        }

        if (!userRepository.existsByUsername(username)){
            throw new ElementNotFoundException("User with username = " + username + " not found!");
        }

        return orderRepository.findOrderByIdAndUserUsername(id, username);
    }
}
