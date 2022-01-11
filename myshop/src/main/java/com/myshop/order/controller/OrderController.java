package com.myshop.order.controller;

import com.myshop.security.exceptions.BadRequestContentException;
import com.myshop.security.exceptions.ElementNotFoundException;
import com.myshop.order.model.Orders;
import com.myshop.order.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orders createOrder(@RequestBody @Valid Orders orders, Errors errors){
        if (errors.hasErrors()) {
            if (errors.getFieldError() != null) {
                throw new BadRequestContentException(errors.getFieldError().getDefaultMessage());
            } else {
                throw new BadRequestContentException();
            }
        }

        return orderService.createOrder(orders);
    }

    @GetMapping(path = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> getAllByUsername(@PathVariable("username") String username){

        List<Orders> orders = orderService.findAllForUser(username);

        if (orders.isEmpty()){
            throw new ElementNotFoundException("No orders for username = " + username);
        }
        return orders;
    }

    @GetMapping(path = "/{username}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Orders findByIdForUsername(@PathVariable("username") String username,
                                      @PathVariable("id") Long id){
        return orderService.findByIdForUser(id, username).map(o ->{
            o.getUser().setPassword(null);
            return o;
        }).orElseThrow(() ->
                new ElementNotFoundException("Orders with id = " + id + " for username = " + username + " not found!"));
    }

}
