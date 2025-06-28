package com.steefy.bookstore.backend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.Category;
import com.steefy.bookstore.repository.AuthorRepository;
import com.steefy.bookstore.repository.BookRepository;
import com.steefy.bookstore.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void BookRepository_Save() {
        
        Author author = Author.builder()
        .name("testAuthorName")
        .build();
        authorRepository.save(author);

        Category category = Category.builder()
        .name("testCategoryName")
        .build();
        categoryRepository.save(category);

        Book book = Book.builder()
        .author(author)
        .category(category)
        .title("testTitle")
        .price(1.0)
        .stock(1)
        .rating(5.0)
        .description("testDescription")
        .publicationDate(LocalDate.of(2025, 01, 01))
        .pageCount(1)
        .coverImageUrl("testCoverImageUrl")
        .build();

        Book savedBook = bookRepository.save(book);

        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isGreaterThan(0);

        Assertions.assertThat(savedBook.getAuthor().getId()).isGreaterThan(0);
        Assertions.assertThat(savedBook.getAuthor().getName()).isEqualTo("testAuthorName");

        Assertions.assertThat(savedBook.getCategory().getId()).isGreaterThan(0);
        Assertions.assertThat(savedBook.getCategory().getName()).isEqualTo("testCategoryName");

        Assertions.assertThat(savedBook.getTitle()).isEqualTo("testTitle");
        Assertions.assertThat(savedBook.getPrice()).isEqualTo(1.0);
        Assertions.assertThat(savedBook.getStock()).isEqualTo(1);
        Assertions.assertThat(savedBook.getRating()).isEqualTo(5.0);
        Assertions.assertThat(savedBook.getDescription()).isEqualTo("testDescription");
        Assertions.assertThat(savedBook.getPublicationDate()).isEqualTo(LocalDate.of(2025, 01, 01));
        Assertions.assertThat(savedBook.getPageCount()).isEqualTo(1);
        Assertions.assertThat(savedBook.getCoverImageUrl()).isEqualTo("testCoverImageUrl");
    }

    @Test
    public void BookRepository_GetById() {

        Author author = Author.builder()
        .name("testAuthorName")
        .build();
        authorRepository.save(author);

        Category category = Category.builder()
        .name("testCategoryName")
        .build();
        categoryRepository.save(category);

        Book book = Book.builder()
        .author(author)
        .category(category)
        .title("testTitle")
        .price(1.0)
        .stock(1)
        .rating(5.0)
        .description("testDescription")
        .publicationDate(LocalDate.of(2025, 01, 01))
        .pageCount(1)
        .coverImageUrl("testCoverImageUrl")
        .build();

        bookRepository.save(book);
        Book savedBook = bookRepository.findById(book.getId()).get();

        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isGreaterThan(0);

        Assertions.assertThat(savedBook.getAuthor().getId()).isGreaterThan(0);
        Assertions.assertThat(savedBook.getAuthor().getName()).isEqualTo("testAuthorName");

        Assertions.assertThat(savedBook.getCategory().getId()).isGreaterThan(0);
        Assertions.assertThat(savedBook.getCategory().getName()).isEqualTo("testCategoryName");

        Assertions.assertThat(savedBook.getTitle()).isEqualTo("testTitle");
        Assertions.assertThat(savedBook.getPrice()).isEqualTo(1.0);
        Assertions.assertThat(savedBook.getStock()).isEqualTo(1);
        Assertions.assertThat(savedBook.getRating()).isEqualTo(5.0);
        Assertions.assertThat(savedBook.getDescription()).isEqualTo("testDescription");
        Assertions.assertThat(savedBook.getPublicationDate()).isEqualTo(LocalDate.of(2025, 01, 01));
        Assertions.assertThat(savedBook.getPageCount()).isEqualTo(1);
        Assertions.assertThat(savedBook.getCoverImageUrl()).isEqualTo("testCoverImageUrl");
    }

    @Test
    public void BookRepository_GetAll() {

        Author author1 = Author.builder()
        .name("testAuthorName1")
        .build();
        authorRepository.save(author1);

        Author author2 = Author.builder()
        .name("testAuthorName2")
        .build();
        authorRepository.save(author2);

        Category category1 = Category.builder()
        .name("testCategoryName1")
        .build();
        categoryRepository.save(category1);

        Category category2 = Category.builder()
        .name("testCategoryName2")
        .build();
        categoryRepository.save(category2);

        Book book1 = Book.builder()
        .author(author1)
        .category(category1)
        .title("testTitle1")
        .price(1.0)
        .stock(1)
        .rating(1.0)
        .description("testDescription1")
        .publicationDate(LocalDate.of(2025, 01, 01))
        .pageCount(1)
        .coverImageUrl("testCoverImageUrl1")
        .build();

        Book book2 = Book.builder()
        .author(author2)
        .category(category2)
        .title("testTitle2")
        .price(2.0)
        .stock(2)
        .rating(2.0)
        .description("testDescription2")
        .publicationDate(LocalDate.of(2025, 02, 02))
        .pageCount(2)
        .coverImageUrl("testCoverImageUrl2")
        .build();

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> books = bookRepository.findAll();

        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isEqualTo(2);

        Assertions.assertThat(books.get(0)).isNotNull();
        Assertions.assertThat(books.get(0).getId()).isGreaterThan(0);

        Assertions.assertThat(books.get(0).getAuthor().getId()).isGreaterThan(0);
        Assertions.assertThat(books.get(0).getAuthor().getName()).isEqualTo("testAuthorName1");

        Assertions.assertThat(books.get(0).getCategory().getId()).isGreaterThan(0);
        Assertions.assertThat(books.get(0).getCategory().getName()).isEqualTo("testCategoryName1");

        Assertions.assertThat(books.get(0).getTitle()).isEqualTo("testTitle1");
        Assertions.assertThat(books.get(0).getPrice()).isEqualTo(1.0);
        Assertions.assertThat(books.get(0).getStock()).isEqualTo(1);
        Assertions.assertThat(books.get(0).getRating()).isEqualTo(1.0);
        Assertions.assertThat(books.get(0).getDescription()).isEqualTo("testDescription1");
        Assertions.assertThat(books.get(0).getPublicationDate()).isEqualTo(LocalDate.of(2025, 01, 01));
        Assertions.assertThat(books.get(0).getPageCount()).isEqualTo(1);
        Assertions.assertThat(books.get(0).getCoverImageUrl()).isEqualTo("testCoverImageUrl1");


        Assertions.assertThat(books.get(1)).isNotNull();
        Assertions.assertThat(books.get(1).getId()).isGreaterThan(0);

        Assertions.assertThat(books.get(1).getAuthor().getId()).isGreaterThan(0);
        Assertions.assertThat(books.get(1).getAuthor().getName()).isEqualTo("testAuthorName2");

        Assertions.assertThat(books.get(1).getCategory().getId()).isGreaterThan(0);
        Assertions.assertThat(books.get(1).getCategory().getName()).isEqualTo("testCategoryName2");

        Assertions.assertThat(books.get(1).getTitle()).isEqualTo("testTitle2");
        Assertions.assertThat(books.get(1).getPrice()).isEqualTo(2.0);
        Assertions.assertThat(books.get(1).getStock()).isEqualTo(2);
        Assertions.assertThat(books.get(1).getRating()).isEqualTo(2.0);
        Assertions.assertThat(books.get(1).getDescription()).isEqualTo("testDescription2");
        Assertions.assertThat(books.get(1).getPublicationDate()).isEqualTo(LocalDate.of(2025, 02, 02));
        Assertions.assertThat(books.get(1).getPageCount()).isEqualTo(2);
        Assertions.assertThat(books.get(1).getCoverImageUrl()).isEqualTo("testCoverImageUrl2");
    }

    @Test
    public void BookRepository_Update() {

        Author author = Author.builder()
        .name("testAuthorName")
        .build();
        authorRepository.save(author);

        Category category = Category.builder()
        .name("testCategoryName")
        .build();
        categoryRepository.save(category);

        Book book = Book.builder()
        .author(author)
        .category(category)
        .title("testTitle")
        .price(1.0)
        .stock(1)
        .rating(1.0)
        .description("testDescription")
        .publicationDate(LocalDate.of(2025, 01, 01))
        .pageCount(1)
        .coverImageUrl("testCoverImageUrl")
        .build();

        bookRepository.save(book);

        Author updatedAuthor = Author.builder()
        .name("updatedTestAuthorName")
        .build();
        authorRepository.save(updatedAuthor);

        Category updatedCategory = Category.builder()
        .name("updatedTestCategoryName")
        .build();
        categoryRepository.save(updatedCategory);

        Book savedBook = bookRepository.findById(book.getId()).get();
        savedBook.setAuthor(updatedAuthor);
        savedBook.setCategory(updatedCategory);
        savedBook.setTitle("updatedTestTitle");
        savedBook.setPrice(2.0);
        savedBook.setStock(2);
        savedBook.setRating(2.0);
        savedBook.setDescription("updatedTestDescription");
        savedBook.setPublicationDate(LocalDate.of(2025, 02, 02));
        savedBook.setPageCount(2);
        savedBook.setCoverImageUrl("updatedTestCoverImageUrl");

        Book updatedBook = bookRepository.save(savedBook);

        Assertions.assertThat(updatedBook).isNotNull();
        Assertions.assertThat(updatedBook.getId()).isGreaterThan(0);

        Assertions.assertThat(updatedBook.getAuthor().getId()).isGreaterThan(0);
        Assertions.assertThat(updatedBook.getAuthor().getName()).isEqualTo("updatedTestAuthorName");

        Assertions.assertThat(updatedBook.getCategory().getId()).isGreaterThan(0);
        Assertions.assertThat(updatedBook.getCategory().getName()).isEqualTo("updatedTestCategoryName");

        Assertions.assertThat(updatedBook.getTitle()).isEqualTo("updatedTestTitle");
        Assertions.assertThat(updatedBook.getPrice()).isEqualTo(2.0);
        Assertions.assertThat(updatedBook.getStock()).isEqualTo(2);
        Assertions.assertThat(updatedBook.getRating()).isEqualTo(2.0);
        Assertions.assertThat(updatedBook.getDescription()).isEqualTo("updatedTestDescription");
        Assertions.assertThat(updatedBook.getPublicationDate()).isEqualTo(LocalDate.of(2025, 02, 02));
        Assertions.assertThat(updatedBook.getPageCount()).isEqualTo(2);
        Assertions.assertThat(updatedBook.getCoverImageUrl()).isEqualTo("updatedTestCoverImageUrl");
    }

    @Test
    public void BookRepository_Delete() {

        Author author = Author.builder()
        .name("testAuthorName")
        .build();
        authorRepository.save(author);

        Category category = Category.builder()
        .name("testCategoryName")
        .build();
        categoryRepository.save(category);

        Book book = Book.builder()
        .author(author)
        .category(category)
        .title("testTitle")
        .price(1.0)
        .stock(1)
        .rating(5.0)
        .description("testDescription")
        .publicationDate(LocalDate.of(2025, 01, 01))
        .pageCount(1)
        .coverImageUrl("testCoverImageUrl")
        .build();

        bookRepository.save(book);
        bookRepository.deleteById(book.getId());

        Optional<Book> deletedBook = bookRepository.findById(book.getId());

        Assertions.assertThat(deletedBook).isEmpty();
    }
}
