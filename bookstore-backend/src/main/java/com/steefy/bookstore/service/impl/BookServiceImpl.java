package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.Category;
import com.steefy.bookstore.exception.ResourceNotFoundException;
import com.steefy.bookstore.mapper.BookMapper;
import com.steefy.bookstore.repository.AuthorRepository;
import com.steefy.bookstore.repository.BookRepository;
import com.steefy.bookstore.repository.CategoryRepository;
import com.steefy.bookstore.service.BookService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId())
        .orElseThrow(() -> new ResourceNotFoundException("Author with id(" + bookDto.getAuthorId() + ") doesn't exist."));

        Category category = categoryRepository.findById(bookDto.getCategoryId())
        .orElseThrow(() -> new ResourceNotFoundException("Category with id(" + bookDto.getCategoryId() + ") doesn't exist."));

        Book book = BookMapper.mapToEntity(bookDto, author, category);
        Book savedBook = bookRepository.save(book);

        return BookMapper.mapToDto(savedBook);
    }

    @Override
    public BookDto getById(Long bookId) {
        Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book with id(" + bookId + ") doesn't exist."));
        return BookMapper.mapToDto(book);
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map((book) -> BookMapper.mapToDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto update(Long bookId, BookDto updatedBookDto) {
        Author author = authorRepository.findById(updatedBookDto.getAuthorId())
        .orElseThrow(() -> new RuntimeException("Author with id(" + updatedBookDto.getAuthorId() + " doesn't exist."));

        Category category = categoryRepository.findById(updatedBookDto.getCategoryId())
        .orElseThrow(() -> new RuntimeException("Category with id(" + updatedBookDto.getCategoryId() + " doesn't exist."));

         Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book with id(" + bookId + ") doesn't exist."));

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

        return BookMapper.mapToDto(updatedBook);
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
        return books.stream().map((book) -> BookMapper.mapToDto(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getAllByAuthor(Author author) {
        List<Book> books = bookRepository.findAllByAuthor(author);
        return books.stream().map((book) -> BookMapper.mapToDto(book)).collect(Collectors.toList());
    }
}