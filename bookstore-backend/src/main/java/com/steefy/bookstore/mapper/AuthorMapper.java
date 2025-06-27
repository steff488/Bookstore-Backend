package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.AuthorDto;
import com.steefy.bookstore.entity.Author;

@Component
public class AuthorMapper {
    public AuthorDto mapToDto(Author author){
        return new AuthorDto(
            author.getId(),
            author.getName()
        );
    }

    public Author mapToEntity(AuthorDto authorDto){
        return Author.builder()
            .id(authorDto.getId())
            .name(authorDto.getName())
            .build();
    }
}