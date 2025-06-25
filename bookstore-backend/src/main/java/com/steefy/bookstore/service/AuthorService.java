package com.steefy.bookstore.service;

import java.util.List;

import com.steefy.bookstore.dto.AuthorDto;

public interface AuthorService {
    AuthorDto create(AuthorDto authorDto);
    AuthorDto getById(Long authorId);
    List<AuthorDto> getAll();
    AuthorDto update(Long authorId, AuthorDto updatedAuthorDto);
    void delete(Long authorId);
}
