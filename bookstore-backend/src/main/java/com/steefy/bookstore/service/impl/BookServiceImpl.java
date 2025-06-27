package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.AuthorDto;
import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.Category;
import com.steefy.bookstore.exception.ResourceNotFoundException;
import com.steefy.bookstore.mapper.AuthorMapper;
import com.steefy.bookstore.mapper.BookMapper;
import com.steefy.bookstore.mapper.CategoryMapper;
import com.steefy.bookstore.repository.BookRepository;
import com.steefy.bookstore.service.BookService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final AuthorServiceImpl authorService;
    private final AuthorMapper authorMapper;

    private final CategoryServiceImpl categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public BookDto create(BookDto bookDto) {

        Book book = bookMapper.mapToEntity(bookDto);
        Book savedBook = bookRepository.save(book);

        return bookMapper.mapToDto(savedBook);
    }

    @Override
    public BookDto getById(Long bookId) {

        Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book with id(" + bookId + ") doesn't exist."));

        return bookMapper.mapToDto(book);
    }

    @Override
    public List<BookDto> getAll() {

        List<Book> books = bookRepository.findAll();

        return books.stream().map((book) -> bookMapper.mapToDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto update(Long bookId, BookDto updatedBookDto) {

        Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book with id(" + bookId + ") doesn't exist."));

        AuthorDto authordto = authorService.getById(updatedBookDto.getAuthorId());
        Author author = authorMapper.mapToEntity(authordto);

        CategoryDto categoryDto = categoryService.getById(updatedBookDto.getCategoryId());
        Category category = categoryMapper.mapToEntity(categoryDto);

        book.setTitle(updatedBookDto.getTitle());
        book.setAuthor(author);
        book.setCategory(category);
        book.setPrice(updatedBookDto.getPrice());
        book.setStock(updatedBookDto.getStock());
        book.setRating(updatedBookDto.getRating());
        book.setDescription(updatedBookDto.getDescription());
        book.setPublicationDate(updatedBookDto.getPublicationDate());
        book.setPageCount(updatedBookDto.getPageCount());
        book.setCoverImageUrl(updatedBookDto.getCoverImageUrl());
        Book updatedBook = bookRepository.save(book);
        
        return bookMapper.mapToDto(updatedBook);
    }

    @Override
    public void delete(Long bookId) {

        bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book with id(" + bookId + ") doesn't exist."));
        
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDto> getAllByCategory(Category category) {

        List<Book> books = bookRepository.findAllByCategory(category);
        return books.stream().map((book) -> bookMapper.mapToDto(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getAllByAuthor(Author author) {

        List<Book> books = bookRepository.findAllByAuthor(author);
        return books.stream().map((book) -> bookMapper.mapToDto(book)).collect(Collectors.toList());
    }
}