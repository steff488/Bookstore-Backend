package com.steefy.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // add
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }

    //getbyid
    //delete
}
