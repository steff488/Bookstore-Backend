package com.steefy.bookstore.mapper;

import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.Category;

public class BookMapper {

    /*  REFACTORING
    // QUESTION: This seems illegal.
    // I suppose mappers should not have access to raw database data?
    private static AuthorRepository authorRepository;
    private static CategoryRepository categoryRepository;
    */

    public static BookDto mapToDto(Book book){
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getAuthor().getId(),
            book.getCategory().getId(),
            book.getPrice(),
            book.getStock(),
            book.getRating(),
            book.getDescription(),
            book.getPublicationDate(),
            book.getPageCount(),
            book.getCoverImageUrl()
        );
    }

    // QUESTION: If i want to ignore the password of a user, 
    // do I need a separate constructor for the User that does not contain the password?

    // public statis Book mapToEntity(BookDto bookDto, Author author, Category category) {...}
    public static Book mapToEntity(BookDto bookDto, Author author, Category category){
        return Book.builder()
        .id(bookDto.getId())
        .title(bookDto.getTitle())
        .author(author)
        .category(category)
        //.author(authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new RuntimeException("TODo")))
        //.category(categoryRepository.findById(bookDto.getCategoryId()).orElseThrow(() -> new RuntimeException("TODO")))
        .price(bookDto.getPrice())
        .stock(bookDto.getStock())
        .rating(bookDto.getRating())
        .description(bookDto.getDescription())
        .publicationDate(bookDto.getPublicationDate())
        .pageCount(bookDto.getPageCount())
        .coverImageUrl(bookDto.getCoverImageUrl())
        .build();
    }
}
