package com.steefy.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.dto.FavoriteItemDto;
import com.steefy.bookstore.service.impl.FavoriteItemServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/favoriteItems")
public class FavoriteItemController {

    private FavoriteItemServiceImpl favoriteItemService;

    // Create favoriteItem
    @PostMapping
    public ResponseEntity<FavoriteItemDto> create(@RequestBody FavoriteItemDto favoriteItemDto){
        FavoriteItemDto savedFavoriteItem = favoriteItemService.create(favoriteItemDto);
        return new ResponseEntity<>(savedFavoriteItem, HttpStatus.CREATED);
    }

    // Get favoriteItem by id
    @GetMapping("{id}")
    public ResponseEntity<FavoriteItemDto> getById(@PathVariable("id") Long favoriteItemId){
        FavoriteItemDto favoriteItemDto = favoriteItemService.getById(favoriteItemId);
        return ResponseEntity.ok(favoriteItemDto);
    }

    // Get all favoriteItems
    @GetMapping
    public ResponseEntity<List<FavoriteItemDto>> getAll(){
        List<FavoriteItemDto> favoriteItemDtos = favoriteItemService.getAll();
        return ResponseEntity.ok(favoriteItemDtos);
    }

    // Get all favoriteItems by userId
    @GetMapping("/by-user")
    public ResponseEntity<List<FavoriteItemDto>> getAllByUserId(@RequestParam() Long userId){
        List<FavoriteItemDto> favoriteItems = favoriteItemService.getAllByUserId(userId);
        return ResponseEntity.ok(favoriteItems);
    }

    // Update favoriteItem
    @PutMapping("{id}")
    public ResponseEntity<FavoriteItemDto> update(@PathVariable("id") Long favoriteItemId, @RequestBody FavoriteItemDto updatedFavoriteItemDto){
        FavoriteItemDto favoriteItemDto = favoriteItemService.update(favoriteItemId, updatedFavoriteItemDto);
        return ResponseEntity.ok(favoriteItemDto);
    }

    // Delete favoriteItem by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long favoriteItemId){
        favoriteItemService.delete(favoriteItemId);
        return ResponseEntity.noContent().build();
    }
}
