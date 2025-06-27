package com.steefy.bookstore.service.impl;

import com.steefy.bookstore.dto.OrderDto;
import com.steefy.bookstore.dto.OrderItemDto;
import com.steefy.bookstore.entity.Order;
import com.steefy.bookstore.entity.OrderItem;
import com.steefy.bookstore.exception.ResourceNotFoundException;
import com.steefy.bookstore.mapper.OrderItemMapper;
import com.steefy.bookstore.mapper.OrderMapper;
import com.steefy.bookstore.repository.OrderRepository;
import com.steefy.bookstore.service.OrderService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderDto create(OrderDto orderDto) {
        
       Order order = orderMapper.mapToEntity(orderDto);

        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
        }
}
       Order savedOrder = orderRepository.save(order);

       return orderMapper.mapToDto(savedOrder);
    }

    @Override
    public OrderDto getById(Long orderId) {
        
        Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new ResourceNotFoundException("Order with id(" + orderId + ") doesn't exist."));
        OrderDto orderDto = orderMapper.mapToDto(order);

        return orderDto;
    }

    @Override
    public List<OrderDto> getAll() {

        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for(Order order : orders)
        {
            orderDtos.add(orderMapper.mapToDto(order));
        }

        return orderDtos;
    }

    @Override
    public OrderDto update(Long orderId, OrderDto updatedOrderDto) {

        Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new ResourceNotFoundException("Order with id(" + orderId + ") doesn't exist."));

        List<OrderItemDto> orderItemDtos = updatedOrderDto.getItems();
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDto orderItemDto : orderItemDtos) {
            items.add(orderItemMapper.mapToEntity(orderItemDto));
        }

        order.setId(orderId);
        order.setTotalPrice(updatedOrderDto.getTotalPrice());
        order.setCreatedAt(updatedOrderDto.getCreatedAt());
        order.setStatus(updatedOrderDto.getStatus());
        order.setItems(items);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.mapToDto(savedOrder);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.findById(orderId)
        .orElseThrow(() -> new ResourceNotFoundException("Order with id(" + orderId + ") doesn't exist."));
        
        orderRepository.deleteById(orderId);
    }
}