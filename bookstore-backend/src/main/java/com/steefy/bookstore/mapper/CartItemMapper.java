package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.entity.CartItem;
import com.steefy.bookstore.dto.CartItemDto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CartItemMapper {

    public CartItemDto mapToDto(CartItem cartItem){
        return new CartItemDto(
            cartItem.getId(),
            cartItem.getUserId(),
            cartItem.getBookId(),
            cartItem.getQuantity(),
            cartItem.getPrice()
            );
    }

    public CartItem mapToEntity(CartItemDto cartItemDto){
        return CartItem.builder()
        .id(cartItemDto.getId())
        .userId(cartItemDto.getUserId())
        .bookId(cartItemDto.getBookId())
        .quantity(cartItemDto.getQuantity())
        .price(cartItemDto.getPrice())
        .build();
    }
}