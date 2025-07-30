package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.entity.FavoriteItem;
import com.steefy.bookstore.dto.FavoriteItemDto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FavoriteItemMapper {

    public FavoriteItemDto mapToDto(FavoriteItem favoriteItem){
        return new FavoriteItemDto(
            favoriteItem.getId(),
            favoriteItem.getUserId(),
            favoriteItem.getBookId()
            );
    }

    public FavoriteItem mapToEntity(FavoriteItemDto favoriteItemDto){
        return FavoriteItem.builder()
        .id(favoriteItemDto.getId())
        .userId(favoriteItemDto.getUserId())
        .bookId(favoriteItemDto.getBookId())
        .build();
    }
}