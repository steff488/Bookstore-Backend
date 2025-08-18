package com.steefy.bookstore.service;

import java.util.List;

import com.steefy.bookstore.dto.CartItemDto;

public interface CartItemService {
    CartItemDto create(CartItemDto cartItemDto);
    CartItemDto getById(Long cartItemDto);
    List<CartItemDto> getAll();
    CartItemDto update(Long cartItemId, CartItemDto updatedCartItemDto);
    void delete(Long cartItemId);
    List<CartItemDto> getAllByUserId(Long userId);
}
