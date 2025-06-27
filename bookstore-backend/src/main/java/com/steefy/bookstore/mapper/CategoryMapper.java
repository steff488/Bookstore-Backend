package com.steefy.bookstore.mapper;

import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToDto(Category Category){
        return new CategoryDto(
            Category.getId(),
            Category.getName()
        );
    }

    public static Category mapToEntity(CategoryDto CategoryDto){
        return Category.builder()
        .id(CategoryDto.getId())
        .name(CategoryDto.getName())
        .build();
    }
}