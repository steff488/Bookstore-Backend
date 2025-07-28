package com.steefy.bookstore.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private Long authorId;
    private Long categoryId;
    private Double price;
    private Integer stock;
    private Double rating;
    private String description;
    private LocalDate publicationDate;
    private Integer pageCount;
    private String coverImageUrl;
}