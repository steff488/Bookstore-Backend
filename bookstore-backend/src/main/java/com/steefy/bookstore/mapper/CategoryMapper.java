package com.steefy.bookstore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.Category;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoryMapper {

    public CategoryDto mapToDto(Category category){
        
        List<Book> books = category.getBooks();
        List<BookDto> bookDtos = new ArrayList<>();
        
        for (Book book : books) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setAuthorId(book.getAuthor().getId());
            bookDto.setCategoryId(book.getCategory().getId());
            bookDto.setPrice(book.getPrice());
            bookDto.setStock(book.getStock());
            bookDto.setRating(book.getRating());
            bookDto.setDescription(book.getDescription());
            bookDto.setPublicationDate(book.getPublicationDate());
            bookDto.setPageCount(book.getPageCount());
            bookDto.setCoverImageUrl(book.getCoverImageUrl());

            bookDtos.add(bookDto);
        }

        return new CategoryDto(
            category.getId(),
            category.getName(),
            bookDtos
        );
    }

    public Category mapToEntity(CategoryDto categoryDto){
        return Category.builder()
        .id(categoryDto.getId())
        .name(categoryDto.getName())
        .build();
    }
}