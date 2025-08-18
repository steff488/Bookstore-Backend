package com.steefy.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.service.impl.CategoryServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryServiceImpl categoryService;

    // Create category
    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.create(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Get category by id
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") Long categoryId){
        CategoryDto categoryDto = categoryService.getById(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categoryDtos = categoryService.getAll();
        return ResponseEntity.ok(categoryDtos);
    }

    // Update category
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable("id") Long categoryId, @RequestBody CategoryDto updatedCategoryDto){
        CategoryDto categoryDto = categoryService.update(categoryId, updatedCategoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    // Delete category by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long categoryId){
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }
}
