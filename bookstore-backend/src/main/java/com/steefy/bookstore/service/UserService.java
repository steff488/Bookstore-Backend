package com.steefy.bookstore.service;

import java.util.List;

import com.steefy.bookstore.dto.UserDto;
import com.steefy.bookstore.dto.UserRegistrationDto;

public interface UserService {
    UserDto create(UserRegistrationDto userRegistrationDto);
    UserDto getById(Long userId);
    List<UserDto> getAll();
    UserDto update(Long userId, UserDto updatedUserDto);
    void delete(Long userId);
}
