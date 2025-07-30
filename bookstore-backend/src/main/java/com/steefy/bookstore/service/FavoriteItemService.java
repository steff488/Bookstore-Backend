package com.steefy.bookstore.service;

import java.util.List;

import com.steefy.bookstore.dto.FavoriteItemDto;

public interface FavoriteItemService {
    FavoriteItemDto create(FavoriteItemDto favoriteItemDto);
    FavoriteItemDto getById(Long favoriteItemDto);
    List<FavoriteItemDto> getAll();
    FavoriteItemDto update(Long favoriteItemId, FavoriteItemDto updatedFavoriteItemDto);
    void delete(Long favoriteItemId);
    List<FavoriteItemDto> getAllByUserId(Long userId);
}
