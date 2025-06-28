package com.steefy.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.steefy.bookstore.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
