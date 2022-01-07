package com.myshop.user.service.interfaces;

import com.myshop.user.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String username);

    User saveUser(User user);
}
