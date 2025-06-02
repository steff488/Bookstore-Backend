package com.steefy.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.steefy.bookstore.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
