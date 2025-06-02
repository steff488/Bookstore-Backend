package com.steefy.bookstore.services;

import com.steefy.bookstore.models.Author;
import com.steefy.bookstore.repositories.AuthorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author add(Author a) {
        return authorRepository.save(a);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
