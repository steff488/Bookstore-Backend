package com.steefy.bookstore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.steefy.bookstore.dto.OrderDto;
import com.steefy.bookstore.dto.OrderItemDto;
import com.steefy.bookstore.dto.UserDto;
import com.steefy.bookstore.entity.Order;
import com.steefy.bookstore.entity.OrderItem;
import com.steefy.bookstore.entity.User;
import com.steefy.bookstore.service.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final UserService userService;
    private final OrderItemMapper orderItemMapper;

    public OrderDto mapToDto(Order order){

        List<OrderItem> orderItems = order.getItems();
        List<OrderItemDto> itemDtos = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
        itemDtos.add(orderItemMapper.mapToDto(orderItem));
        }

        return new OrderDto(
            order.getId(),
            order.getUser().getId(),
            order.getTotalPrice(),
            order.getCreatedAt(),
            order.getStatus(),
            itemDtos
        );
    }

    public Order mapToEntity(OrderDto orderDto){

        UserDto userDto = userService.getById(orderDto.getUserId());

        // Manually map user
        User user = User.builder()
            .id(userDto.getId())
            .userName(userDto.getUserName())
            .email(userDto.getEmail())
            .role(userDto.getRole())
            .build();
        
        List<OrderItemDto> orderItemDtos = orderDto.getItems();
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDto orderItemDto : orderItemDtos) {
            items.add(orderItemMapper.mapToEntity(orderItemDto));
        }

        return Order.builder()
            .id(orderDto.getId())
            .totalPrice(orderDto.getTotalPrice())
            .createdAt(orderDto.getCreatedAt())
            .status(orderDto.getStatus())
            .user(user)
            .items(items)
            .build();
    }
}