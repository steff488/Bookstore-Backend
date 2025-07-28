package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.CartItemDto;
import com.steefy.bookstore.entity.CartItem;
import com.steefy.bookstore.mapper.CartItemMapper;
import com.steefy.bookstore.repository.CartItemRepository;
import com.steefy.bookstore.service.CartItemService;
import com.steefy.bookstore.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemDto create(CartItemDto cartItemDto) {

        CartItem cartItem = cartItemMapper.mapToEntity(cartItemDto);
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        return cartItemMapper.mapToDto(savedCartItem);
    }

    @Override
    public CartItemDto getById(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
        .orElseThrow(() -> new ResourceNotFoundException("CartItem with id(" + cartItemId + ") doesn't exist."));

        return cartItemMapper.mapToDto(cartItem);
    }

    @Override
    public List<CartItemDto> getAll() {

        List<CartItem> categories = cartItemRepository.findAll();
        return categories.stream().map((cartItem) -> cartItemMapper.mapToDto(cartItem)).collect(Collectors.toList());
    }

    @Override
    public CartItemDto update(Long cartItemId, CartItemDto updatedCartItemDto) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
        .orElseThrow(() -> new ResourceNotFoundException("CartItem with id(" + cartItemId + ") doesn't exist."));

        cartItem.setQuantity(updatedCartItemDto.getQuantity());
        CartItem updatedCartItem = cartItemRepository.save(cartItem);

        return cartItemMapper.mapToDto(updatedCartItem);
    }

    @Override
    public void delete(Long cartItemId) {

        cartItemRepository.findById(cartItemId)
        .orElseThrow(() -> new ResourceNotFoundException("CartItem with id(" + cartItemId + ") doesn't exist."));

        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItemDto> getAllByUserId(Long userId) {

        List<CartItem> cartItems  = cartItemRepository.findAllByUserId(userId);
        return cartItems.stream().map((cartItem) -> cartItemMapper.mapToDto(cartItem)).collect(Collectors.toList());
    }
}
