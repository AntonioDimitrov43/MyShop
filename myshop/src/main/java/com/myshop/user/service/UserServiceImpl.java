package com.myshop.user.service;

import com.myshop.exceptions.BadIdException;
import com.myshop.user.model.User;
import com.myshop.user.repository.UserRepository;
import com.myshop.user.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        return userRepository.findById(username);
    }

    @Override
    public User saveUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be Null during Save!");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("User with username = " + user.getUsername() + " already exists!");
        }

        return userRepository.save(user);
    }
}
