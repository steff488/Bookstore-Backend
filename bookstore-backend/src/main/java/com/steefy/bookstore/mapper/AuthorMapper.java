package com.steefy.bookstore.mapper;

import com.steefy.bookstore.dto.AuthorDto;
import com.steefy.bookstore.entity.Author;

public class AuthorMapper {
    public static AuthorDto mapToDto(Author author){
        return new AuthorDto(
            author.getId(),
            author.getName()
        );
    }

    public static Author mapToEntity(AuthorDto authorDto){
        return new Author(
            authorDto.getId(),
            authorDto.getName()
        );
    }
}
