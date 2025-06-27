package com.steefy.bookstore.dto;

import com.steefy.bookstore.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String userName;
    private String email;
    private Role role;
    private String password;
}
