package com.myshop.user.controller;

import com.myshop.exceptions.BadRequestContentException;
import com.myshop.exceptions.ElementNotFoundException;
import com.myshop.user.model.User;
import com.myshop.user.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(String username){
        return userService.getUser(username).orElseThrow(() -> new ElementNotFoundException("User with username = "
        + username + " not exist!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            if (errors.getFieldError()!= null){
                throw new BadRequestContentException(errors.getFieldError().getDefaultMessage());
            } else{
                throw new BadRequestContentException();
            }
        }

        return userService.saveUser(user);
    }


}
