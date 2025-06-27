package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.AuthorDto;
import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.Category;
import com.steefy.bookstore.service.AuthorService;
import com.steefy.bookstore.service.CategoryService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookMapper {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public BookDto mapToDto(Book book){
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

    public Book mapToEntity(BookDto bookDto){

        AuthorDto authorDto = authorService.getById(bookDto.getAuthorId());
        Author author = authorMapper.mapToEntity(authorDto);

        CategoryDto categoryDto = categoryService.getById(bookDto.getCategoryId());
        Category category = categoryMapper.mapToEntity(categoryDto);

        return Book.builder()
        .id(bookDto.getId())
        .title(bookDto.getTitle())
        .author(author)
        .category(category)
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
