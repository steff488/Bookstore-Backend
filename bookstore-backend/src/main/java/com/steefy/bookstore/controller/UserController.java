package com.steefy.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.entity.User;
import com.steefy.bookstore.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //add
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
    //getbyid
    //delete
}
