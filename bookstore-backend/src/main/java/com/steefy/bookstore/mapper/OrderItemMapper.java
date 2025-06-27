package com.steefy.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.BookDto;
import com.steefy.bookstore.dto.OrderItemDto;
import com.steefy.bookstore.entity.Book;
import com.steefy.bookstore.entity.OrderItem;
import com.steefy.bookstore.service.BookService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderItemMapper {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public OrderItemDto mapToDto(OrderItem orderItem){
        return new OrderItemDto(
            orderItem.getId(),
            orderItem.getBook().getId(),
            orderItem.getQuantity(),
            orderItem.getPriceAtPurchase()
        );
    }

    public OrderItem mapToEntity(OrderItemDto orderItemDto){

        BookDto bookDto = bookService.getById(orderItemDto.getBookId());
        Book book = bookMapper.mapToEntity(bookDto);

        return OrderItem.builder()
            .id(orderItemDto.getId())
            .book(book)
            .quantity(orderItemDto.getQuantity())
            .priceAtPurchase(orderItemDto.getPriceAtPurchase())
            .build();
    }
}