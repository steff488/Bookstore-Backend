package com.steefy.bookstore.service;

import java.util.List;

import com.steefy.bookstore.dto.BookDto;

public interface BookService {
    BookDto create(BookDto bookDto);
    BookDto getById(Long bookDto);
    List<BookDto> getAll();
    List<BookDto> getAllByAuthorId(Long authorId);
    List<BookDto> getAllByCategoryId(Long categoryId);
    List<BookDto> getByTitleContainingIgnoreCase(String title);
    BookDto update(Long bookId, BookDto updatedBookDto);
    void delete(Long bookId);
}
