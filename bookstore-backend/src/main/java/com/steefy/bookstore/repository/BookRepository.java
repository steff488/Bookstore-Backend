package com.steefy.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.steefy.bookstore.entity.Author;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByCategory(Category category);
    List<Book> findAllByAuthor(Author author);
}
