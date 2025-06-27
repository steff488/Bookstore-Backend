package com.steefy.bookstore.dto;

import java.time.LocalDate;
import java.util.List;

import com.steefy.bookstore.entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private LocalDate createdAt;
    private OrderStatus status;
    private List<OrderItemDto> items;
}