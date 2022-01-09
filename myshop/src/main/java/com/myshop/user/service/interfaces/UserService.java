package com.myshop.user.service.interfaces;

import com.myshop.user.helper.ChangePassHelper;
import com.myshop.user.model.User;

import java.util.Optional;

public interface UserService {
    /**
     * This is a user service method for getting a user when username is not null or blank
     * @param username valid not null or blank username
     * @return found user
     */
    Optional<User> getUser(String username);

    /**
     * This is a user service method for creating user when user is  not null and username does not exist
     * and encode user password
     * @param user valid not null user
     * @return saved user
     */
    User saveUser(User user);

    User changePassword(ChangePassHelper passHelper);
}
