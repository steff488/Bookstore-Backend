package com.steefy.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.dto.CartItemDto;
import com.steefy.bookstore.service.impl.CartItemServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cartItems")
public class CartItemController {

    private CartItemServiceImpl cartItemService;

    // Create cartItem
    @PostMapping
    public ResponseEntity<CartItemDto> create(@RequestBody CartItemDto cartItemDto){
        CartItemDto savedCartItem = cartItemService.create(cartItemDto);
        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
    }

    // Get cartItem by id
    @GetMapping("{id}")
    public ResponseEntity<CartItemDto> getById(@PathVariable("id") Long cartItemId){
        CartItemDto cartItemDto = cartItemService.getById(cartItemId);
        return ResponseEntity.ok(cartItemDto);
    }

    // Get all cartItems
    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAll(){
        List<CartItemDto> cartItemDtos = cartItemService.getAll();
        return ResponseEntity.ok(cartItemDtos);
    }

    // Get all cartItems by userId
    @GetMapping("/by-user")
    public ResponseEntity<List<CartItemDto>> getAllByUserId(@PathVariable Long userId){
        List<CartItemDto> cartItems = cartItemService.getAllByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    // Update cartItem
    @PutMapping("{id}")
    public ResponseEntity<CartItemDto> update(@PathVariable("id") Long cartItemId, @RequestBody CartItemDto updatedCartItemDto){
        CartItemDto cartItemDto = cartItemService.update(cartItemId, updatedCartItemDto);
        return ResponseEntity.ok(cartItemDto);
    }

    // Delete cartItem by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long cartItemId){
        cartItemService.delete(cartItemId);
        return ResponseEntity.ok("CartItem deleted succesfully!");
    }
}
