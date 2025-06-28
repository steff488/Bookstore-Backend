package com.steefy.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.steefy.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthorId(Long authorId);
    List<Book> findAllByCategoryId(Long categoryId);
}
