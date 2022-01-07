package com.myshop.cart.controller;

import com.myshop.cart.model.CartItem;
import com.myshop.cart.repository.CartItemRepository;
import com.myshop.cart.service.CartItemService;
import com.myshop.exceptions.BadRequestContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cartitem")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping(path = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> getCart(@PathVariable("username") String username){
        return cartItemService.getCart(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItem saveCartItem(@RequestBody @Valid CartItem cartItem, Errors errors){
        if (errors.hasErrors()){
            if (errors.getFieldError() != null) {
                throw new BadRequestContentException(errors.getFieldError().getDefaultMessage());
            } else {
                throw new BadRequestContentException();
            }
        }

        return cartItemService.saveCartItem(cartItem);
    }
}
