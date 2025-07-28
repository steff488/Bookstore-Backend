package com.steefy.bookstore.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;

import com.steefy.bookstore.dto.AuthorDto;
import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.mapper.AuthorMapper;
import com.steefy.bookstore.repository.AuthorRepository;
import com.steefy.bookstore.service.impl.AuthorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorServiceImpl authorService;

    private AuthorMapper authorMapper = new AuthorMapper();

    @BeforeEach
    void setup() {
        authorService = new AuthorServiceImpl(authorRepository, authorMapper);
    }

    @Test
    public void AuthorService_create() {

        Author author = Author.builder()
        .name("testName")
        .build();
        AuthorDto authorDto = authorMapper.mapToDto(author);

        when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);

        AuthorDto savedAuthorDto = authorService.create(authorDto);

        Assertions.assertThat(savedAuthorDto).isNotNull();
        Assertions.assertThat(savedAuthorDto.getName()).isEqualTo("testName");
    }

    @Test
    public void AuthorService_getAll() {

        Author author1 = Author.builder()
        .name("testName1")
        .build();

        Author author2 = Author.builder()
        .name("testName2")
        .build();

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        Mockito.when(authorRepository.findAll()).thenReturn(authors);

        List<AuthorDto> savedAuthorDtos = authorService.getAll();

        Assertions.assertThat(savedAuthorDtos).isNotNull();
        Assertions.assertThat(savedAuthorDtos.size()).isEqualTo(2);

        Assertions.assertThat(savedAuthorDtos.get(0).getName()).isEqualTo("testName1");
        Assertions.assertThat(savedAuthorDtos.get(1).getName()).isEqualTo("testName2");
    }

    @Test
    public void AuthorService_getById() {

        Author author = Author.builder()
        .name("testName")
        .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(author));

        AuthorDto savedAuthorDto = authorService.getById(1L);

        Assertions.assertThat(savedAuthorDto).isNotNull();
        Assertions.assertThat(savedAuthorDto.getName()).isEqualTo("testName");
    }

    @Test
    public void AuthorService_update() {

        Author author = Author.builder()
        .name("testName")
        .build();
        AuthorDto authorDto = authorMapper.mapToDto(author);

        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(author));
        when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);

        AuthorDto savedAuthorDto = authorService.update(1L, authorDto);

        Assertions.assertThat(savedAuthorDto).isNotNull();
        Assertions.assertThat(savedAuthorDto.getName()).isEqualTo("testName");
    }

    @Test
    public void AuthorService_delete() {

        Author author = Author.builder()
        .name("testName")
        .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(author));

        assertAll(() -> authorService.delete(1L));
    }
}
