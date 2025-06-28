package com.steefy.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
}
