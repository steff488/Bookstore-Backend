package com.steefy.bookstore.mapper;

import com.steefy.bookstore.dto.UserDto;
import com.steefy.bookstore.dto.UserRegistrationDto;
import com.steefy.bookstore.entity.Role;
import com.steefy.bookstore.entity.User;

public class UserMapper {
    public static UserDto mapToDto(User user){
        return new UserDto(
            user.getId(),
            user.getUserName(),
            user.getEmail(),
            user.getRole()
        );
    }

    // QUESTION: Is this fine?
    public static User mapToEntity(UserDto userDto){
        return User.builder()
        .id(userDto.getId())
        .userName(userDto.getUserName())
        .email(userDto.getEmail())
        .role(userDto.getRole())
        .build();
    }

    public static User mapToEntity(UserRegistrationDto registrationDto) {
        return User.builder()
        .userName(registrationDto.getUserName())
        .email(registrationDto.getEmail())
        .password(registrationDto.getPassword())
        .role(Role.ROLE_USER)   // Default role
        .build();
    }
}