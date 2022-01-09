package com.myshop.user.service;

import com.myshop.exceptions.ElementNotFoundException;
import com.myshop.exceptions.IncorrectPasswordException;
import com.myshop.user.helper.ChangePassHelper;
import com.myshop.user.model.User;
import com.myshop.user.repository.UserRepository;
import com.myshop.user.service.interfaces.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This is a user service method for getting a user when username is not null or blank
     * @param username valid not null or blank username
     * @return found user
     */
    @Override
    public Optional<User> getUser(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        return userRepository.findById(username);
    }

    /**
     * This is a user service method for creating user when user is  not null and username does not exist
     * and encode user password
     * @param user valid not null user
     * @return saved user
     */
    @Override
    public User saveUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be Null during Save!");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("User with username = " + user.getUsername() + " already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User changePassword(ChangePassHelper passHelper) {
        if (passHelper == null){
            log.debug("User credentials for changing password cannot be null!");
            throw new IllegalArgumentException("Date for changing password cannot be null");
        }
        log.debug("Try to get user with username = " + passHelper.getUsername());
        Optional<User> user = userRepository.findByUsername(passHelper.getUsername());
        if(user.isPresent()){

            if (!passHelper.getNewPassword().equals(passHelper.getNewPasswordAgain())){
                log.warn("New password and repeated new password not match!");
                throw new IllegalArgumentException("New password and repeated new password not match!");
            }
            if(passwordEncoder.matches(passHelper.getOldPassword(), user.get().getPassword())){
                log.debug("set new password encoded");
                user.get().setPassword(passwordEncoder.encode(passHelper.getNewPassword()));

                log.debug("Save changed password");
                return userRepository.save(user.get());
            }else {
                log.warn("Incorrect  old password!");
                throw new IncorrectPasswordException();
            }

        }else  {
            log.warn("No user with username = " +passHelper.getUsername() );
            throw new ElementNotFoundException("No user with username = " + passHelper.getUsername());
        }
    }
}
