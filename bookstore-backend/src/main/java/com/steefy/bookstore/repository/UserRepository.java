package com.steefy.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.steefy.bookstore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
