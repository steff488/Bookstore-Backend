package com.steefy.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.mapper.CategoryMapper;
import com.steefy.bookstore.service.impl.BookServiceImpl;
import com.steefy.bookstore.service.impl.CategoryServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private BookServiceImpl bookService;
    private CategoryServiceImpl categoryService;

    // Create book
    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto){
        BookDto savedBook = bookService.create(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // Get book by id
    @GetMapping("{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long bookId){
        BookDto bookDto = bookService.getById(bookId);
        return ResponseEntity.ok(bookDto);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(){
        List<BookDto> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    // Update book
    @PutMapping("{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") Long bookId, @RequestBody BookDto updatedBookDto){
        BookDto bookDto = bookService.update(bookId, updatedBookDto);
        return ResponseEntity.ok(bookDto);
    }

    // Delete book by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long bookId){
        bookService.delete(bookId);
        return ResponseEntity.ok("Book deleted succesfully!");
    }

    // Get all books by category
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<BookDto>> getAllByCategory(@PathVariable String categoryName){
        CategoryDto categoryDto = categoryService.getByName(categoryName);
        List<BookDto> books = bookService.getAllByCategory(CategoryMapper.mapToEntity(categoryDto));
        return ResponseEntity.ok(books);
    }
    
    // QUESTION: Is this better? - Will imply to have both getAllByCategory and getAllByCategoryName
    // Should I only have getAllByCategoryName instead?
    /*
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<BookDto>> getAllByCategory(@PathVariable String categoryName) {
        List<BookDto> books = bookService.getAllByCategoryName(categoryName);
        return ResponseEntity.ok(books);
    }
    */
}
