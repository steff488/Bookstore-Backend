package com.steefy.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.dto.UserDto;
import com.steefy.bookstore.dto.UserRegistrationDto;
import com.steefy.bookstore.service.impl.UserServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserServiceImpl userService;

    // Create user
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserRegistrationDto userRegistrationDto){
        UserDto savedUser = userService.create(userRegistrationDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Get user by id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long userId){
        UserDto userDto = userService.getById(userId);
        return ResponseEntity.ok(userDto);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    // Update user
    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long userId, @RequestBody UserDto updatedUserDto){
        UserDto userDto = userService.update(userId, updatedUserDto);
        return ResponseEntity.ok(userDto);
    }

    // Delete user by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long userId){
        userService.delete(userId);
        return ResponseEntity.ok("User deleted succesfully!");
    }
}
