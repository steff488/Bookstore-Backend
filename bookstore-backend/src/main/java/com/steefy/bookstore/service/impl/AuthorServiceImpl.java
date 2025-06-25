package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.AuthorDto;
import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.mapper.AuthorMapper;
import com.steefy.bookstore.repository.AuthorRepository;
import com.steefy.bookstore.service.AuthorService;
import com.steefy.bookstore.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author = AuthorMapper.mapToEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.mapToDto(savedAuthor);
    }

    @Override
    public AuthorDto getById(Long authorId) {
        Author author = authorRepository.findById(authorId)
        .orElseThrow(() -> new ResourceNotFoundException("Author with id(" + authorId + ") doesn't exist."));
        return AuthorMapper.mapToDto(author);
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map((author) -> AuthorMapper.mapToDto(author)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto update(Long authorId, AuthorDto updatedAuthorDto) {
        Author author = authorRepository.findById(authorId)
        .orElseThrow(() -> new ResourceNotFoundException("Author with id(" + authorId + ") doesn't exist."));

        author.setName(updatedAuthorDto.getName());
        Author updatedAuthor = authorRepository.save(author);

        return AuthorMapper.mapToDto(updatedAuthor);
    }

    @Override
    public void delete(Long authorId) {
        authorRepository.findById(authorId)
        .orElseThrow(() -> new ResourceNotFoundException("Author with id(" + authorId + ") doesn't exist."));

        authorRepository.deleteById(authorId);
    }
}
