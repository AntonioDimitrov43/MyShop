package com.myshop.cart.service;

import com.myshop.cart.model.CartItem;
import com.myshop.cart.repository.CartItemRepository;
import com.myshop.exceptions.ElementNotFoundException;
import com.myshop.product.model.Product;
import com.myshop.product.repository.ProductRepository;
import com.myshop.product.service.ProductService;
import com.myshop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;


    @Autowired
    public CartItemServiceImpl(UserRepository userRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Override
    public List<CartItem> getCart(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank and null!");
        }
        if (!userRepository.existsByUsername(username)) {
            throw new ElementNotFoundException("User with username = " + username + " not found!");
        }

        return cartItemRepository.findAllByUserUsername(username);

    }

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        if (cartItem == null) {
            throw new IllegalArgumentException("Cannot be null when saving!");
        }
        if (!userRepository.existsByUsername(cartItem.getUser().getUsername())) {
            throw new ElementNotFoundException("User with username = " + cartItem.getUser().getUsername() + " does not exist!");
        }
        if (!productRepository.existsById(cartItem.getProduct().getId())){
            throw new ElementNotFoundException("Product with Id =" + cartItem.getProduct().getId() + " does not exist!");
        }

        cartItem.setUser(userRepository.getByUsername(cartItem.getUser().getUsername()));

        productService.removeQuantity(cartItem.getQuantity(), cartItem.getProduct().getId());

        return cartItemRepository.save(cartItem);

    }
}
