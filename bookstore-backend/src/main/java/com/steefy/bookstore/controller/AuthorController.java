package com.steefy.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.dto.AuthorDto;
import com.steefy.bookstore.service.impl.AuthorServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorServiceImpl authorService;

    // Create author
    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto){
        AuthorDto savedAuthor = authorService.create(authorDto);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    // Get author by id
    @GetMapping("{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") Long authorId){
        AuthorDto authorDto = authorService.getById(authorId);
        return ResponseEntity.ok(authorDto);
    }

    // Get all authors
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll(){
        List<AuthorDto> authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    // Update author
    @PutMapping("{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable("id") Long authorId, @RequestBody AuthorDto updatedAuthorDto){
        AuthorDto authorDto = authorService.update(authorId, updatedAuthorDto);
        return ResponseEntity.ok(authorDto);
    }

    // Delete author by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long authorId){
        authorService.delete(authorId);
        return ResponseEntity.ok("Author deleted succesfully!");
    }
}
