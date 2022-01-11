package com.myshop.cart.service;

import com.myshop.cart.model.CartItem;
import com.myshop.cart.repository.CartItemRepository;
import com.myshop.security.exceptions.ElementNotFoundException;
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

    /**
     * Checks and finds all cart items assigned to a valid username if such a username exists
     * @param username not null or blank username
     * @return List of all found cart items
     */
    @Override
    public List<CartItem> getCartForUser(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank and null!");
        }
        if (!userRepository.existsByUsername(username)) {
            throw new ElementNotFoundException("User with username = " + username + " not found!");
        }

        return cartItemRepository.findAllByUserUsername(username);

    }

    /**
     * If person with username and specific product exist it adds them to the cart list.
     * @param cartItem not null cart item
     * @return Lowers product amount in the system and adds a cart item to the list and assigns the username to it
     */
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

    /**
     * Sends all carts items, that specific user with existing username has in their cart, to the order entry.
     * @param username not null and not blank username
     */
    @Override
    public void moveToOrderEntryByUsername(String username) {
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username is mandatory and cannot be blank!");
        }
        if (!userRepository.existsByUsername(username)){
            throw new ElementNotFoundException("User with username = " + username + " not found!");
        }
        List<CartItem> cartItems = cartItemRepository.findAll();

        cartItems.stream()
                .filter(item -> item.getUser().getUsername().equals(username))
                .forEach(item -> cartItemRepository.deleteById(item.getId()));
    }

}
