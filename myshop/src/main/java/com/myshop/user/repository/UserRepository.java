package com.myshop.user.repository;

import com.myshop.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * This is a method which checks if a user exists
     * @param username not null or blank username
     * @return if user with given username exists
     */
    boolean existsByUsername(String username);

    /**
     * This is a method which finds user by username
     * @param username not null or blank username
     * @return found user
     */
    User getByUsername(String username);

    Optional<User> findByUsername(String username);
}
