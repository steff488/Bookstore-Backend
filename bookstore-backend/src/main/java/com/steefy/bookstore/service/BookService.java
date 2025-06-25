package com.steefy.bookstore.service;

import java.util.List;

import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.entity.Category;

public interface BookService {
    BookDto create(BookDto bookDto);
    BookDto getById(Long bookDto);
    List<BookDto> getAll();
    BookDto update(Long bookId, BookDto updatedBookDto);
    void delete(Long bookId);
    List<BookDto> getAllByCategory(Category category);
    List<BookDto> getAllByAuthor(Author author);
}
