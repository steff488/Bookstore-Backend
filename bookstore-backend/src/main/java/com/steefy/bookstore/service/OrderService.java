package com.steefy.bookstore.service;

import java.util.List;

import com.steefy.bookstore.dto.OrderDto;

public interface OrderService {
    OrderDto create(OrderDto orderDto);
    OrderDto getById(Long orderId);
    List<OrderDto> getAll();
    List<OrderDto> getAllByUserId(Long userId);
    OrderDto update(Long orderId, OrderDto updatedOrderDto);
    void delete(Long orderId);
}
