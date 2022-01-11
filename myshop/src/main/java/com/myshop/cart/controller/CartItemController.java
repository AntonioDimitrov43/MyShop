package com.myshop.cart.controller;

import com.myshop.cart.model.CartItem;
import com.myshop.cart.service.CartItemService;
import com.myshop.security.exceptions.BadRequestContentException;
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

    /**
     * This is a Cart Item controller used to get a user's cart information.
     * @param username path variable used to find all of the Products related to the user.
     * @return a List of all of the products found that are related to the user.
     */
    @GetMapping(path = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartItem> getCart(@PathVariable("username") String username){
        return cartItemService.getCartForUser(username);
    }

    /**
     * This is a Cart Item controller used to save a product to a specific cart or a new one if one doesnt exist already
     * @param cartItem Valid cart item from JSON request  body
     * @param errors for invalid cart item JSON request body
     * @return saves a product to a cart
     */
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
