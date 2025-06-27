package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.UserDto;
import com.steefy.bookstore.dto.UserRegistrationDto;
import com.steefy.bookstore.entity.UserRole;
import com.steefy.bookstore.entity.User;

@Component
public class UserMapper {
    
    public UserDto mapToDto(User user){
        return new UserDto(
            user.getId(),
            user.getUserName(),
            user.getEmail(),
            user.getRole()
        );
    }

    public User mapToEntity(UserRegistrationDto registrationDto) {
        return User.builder()
        .userName(registrationDto.getUserName())
        .email(registrationDto.getEmail())
        .password(registrationDto.getPassword())
        .role(UserRole.ROLE_USER)   // Default role
        .build();
    }
}