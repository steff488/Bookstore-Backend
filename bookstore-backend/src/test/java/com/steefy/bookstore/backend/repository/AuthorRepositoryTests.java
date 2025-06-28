package com.steefy.bookstore.backend.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.repository.AuthorRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorRepositoryTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void AuthorRepository_Save() {
        
        Author author = Author.builder()
        .name("testName")
        .build();

        Author savedAuthor = authorRepository.save(author);

        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getId()).isGreaterThan(0);
        Assertions.assertThat(savedAuthor.getName()).isEqualTo("testName");
    }

    @Test
    public void AuthorRepository_GetById() {

        Author author = Author.builder()
        .name("testName")
        .build();

        authorRepository.save(author);
        Author savedAuthor = authorRepository.findById(author.getId()).get();

        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getId()).isGreaterThan(0);
        Assertions.assertThat(savedAuthor.getName()).isEqualTo("testName");
    }

    @Test
    public void AuthorRepository_GetAll() {

        Author author1 = Author.builder()
        .name("testName1")
        .build();

        Author author2 = Author.builder()
        .name("testName2")
        .build();

        authorRepository.save(author1);
        authorRepository.save(author2);

        List<Author> authors = authorRepository.findAll();

        Assertions.assertThat(authors).isNotNull();
        Assertions.assertThat(authors.size()).isEqualTo(2);

        Assertions.assertThat(authors.get(0).getId()).isGreaterThan(0);
        Assertions.assertThat(authors.get(0).getName()).isEqualTo("testName1");

        Assertions.assertThat(authors.get(1).getId()).isGreaterThan(0);
        Assertions.assertThat(authors.get(1).getName()).isEqualTo("testName2");
    }

    @Test
    public void AuthorRepository_Update() {

        Author author = Author.builder()
        .name("testName")
        .build();

        authorRepository.save(author);

        Author savedAuthor = authorRepository.findById(author.getId()).get();
        savedAuthor.setName("updatedTestName");

        Author updatedAuthor = authorRepository.save(savedAuthor);

        Assertions.assertThat(updatedAuthor).isNotNull();
        Assertions.assertThat(updatedAuthor.getId()).isGreaterThan(0);
        Assertions.assertThat(updatedAuthor.getName()).isEqualTo("updatedTestName");
    }

    @Test
    public void AuthorRepository_Delete() {

        Author author = Author.builder()
        .name("testName")
        .build();

        authorRepository.save(author);
        authorRepository.deleteById(author.getId());

        Optional<Author> deletedAuthor = authorRepository.findById(author.getId());

        Assertions.assertThat(deletedAuthor).isEmpty();
    }
}
