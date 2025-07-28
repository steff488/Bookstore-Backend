package com.steefy.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteItemDto {
    private Long id;
    private Long userId;
    private Long bookId;
    private Double price;
}
