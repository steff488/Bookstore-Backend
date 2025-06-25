    package com.steefy.bookstore.service;

    import java.util.List;

    import com.steefy.bookstore.dto.CategoryDto;

    public interface CategoryService {
        CategoryDto create(CategoryDto categoryDto);
        CategoryDto getById(Long categoryId);
        List<CategoryDto> getAll();
        CategoryDto update(Long categoryId, CategoryDto updatedCategoryDto);
        void delete(Long categoryId);
        CategoryDto getByName(String name);
    }
