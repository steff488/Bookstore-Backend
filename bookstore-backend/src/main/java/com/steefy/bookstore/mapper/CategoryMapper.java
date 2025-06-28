package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.entity.Category;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoryMapper {
    public CategoryDto mapToDto(Category category){
        return new CategoryDto(
            category.getId(),
            category.getName()
        );
    }

    public Category mapToEntity(CategoryDto categoryDto){
        return Category.builder()
        .id(categoryDto.getId())
        .name(categoryDto.getName())
        .build();
    }
}