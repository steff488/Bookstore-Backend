package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.entity.Category;
import com.steefy.bookstore.mapper.CategoryMapper;
import com.steefy.bookstore.repository.CategoryRepository;
import com.steefy.bookstore.service.CategoryService;
import com.steefy.bookstore.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToDto(savedCategory);
    }

    @Override
    public CategoryDto getById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category with id(" + categoryId + ") doesn't exist."));
        return CategoryMapper.mapToDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categorys = categoryRepository.findAll();
        return categorys.stream().map((category) -> CategoryMapper.mapToDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto update(Long categoryId, CategoryDto updatedCategoryDto) {
        Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category with id(" + categoryId + ") doesn't exist."));

        category.setName(updatedCategoryDto.getName());
        Category updatedCategory = categoryRepository.save(category);

        return CategoryMapper.mapToDto(updatedCategory);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category with id(" + categoryId + ") doesn't exist."));

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto getByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name)
        .orElseThrow(() -> new ResourceNotFoundException("Category with name '" + name + "' doesn't exist."));
        return CategoryMapper.mapToDto(category);
    }
}
