package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.CategoryDto;
import com.steefy.bookstore.entity.Category;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoryMapper {

    /*
    @Lazy
    private BookMapper bookMapper;
    */

    public CategoryDto mapToDto(Category category){
        
        // List<Book> books = category.getBooks();
        // List<BookDto> bookDtos = new ArrayList<>();

        /*
        for (Book book : books) {
            bookDtos.add(bookMapper.mapToDto(book));
        }
        */

        return new CategoryDto(
            category.getId(),
            category.getName()
            //bookDtos
        );
    }

    public Category mapToEntity(CategoryDto categoryDto){
        return Category.builder()
        .id(categoryDto.getId())
        .name(categoryDto.getName())
        .build();
    }
}