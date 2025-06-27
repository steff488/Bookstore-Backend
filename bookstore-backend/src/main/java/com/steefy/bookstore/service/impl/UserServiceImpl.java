package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.UserDto;
import com.steefy.bookstore.dto.UserRegistrationDto;
import com.steefy.bookstore.entity.User;
import com.steefy.bookstore.mapper.UserMapper;
import com.steefy.bookstore.repository.UserRepository;
import com.steefy.bookstore.service.UserService;
import com.steefy.bookstore.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto create(UserRegistrationDto userRegistrationDto) {
        User user = UserMapper.mapToEntity(userRegistrationDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToDto(savedUser);
    }

    @Override
    public UserDto getById(Long userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User with id(" + userId + ") doesn't exist."));
        return UserMapper.mapToDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long userId, UserDto updatedUserDto) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User with id(" + userId + ") doesn't exist."));

        user.setUserName(updatedUserDto.getUserName());
        user.setEmail(updatedUserDto.getEmail());
        user.setRole(updatedUserDto.getRole());
        User updatedUser = userRepository.save(user);

        return UserMapper.mapToDto(updatedUser);
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User with id(" + userId + ") doesn't exist."));

        userRepository.deleteById(userId);
    }
}
