package com.steefy.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.steefy.bookstore.dto.OrderDto;
import com.steefy.bookstore.service.impl.OrderServiceImpl;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private OrderServiceImpl orderService;

    // Create order
    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto){
        OrderDto savedOrder = orderService.create(orderDto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Get order by id
    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable("id") Long orderId){
        OrderDto orderDto = orderService.getById(orderId);
        return ResponseEntity.ok(orderDto);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(){
        List<OrderDto> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    // Update order
    @PutMapping("{id}")
    public ResponseEntity<OrderDto> update(@PathVariable("id") Long orderId, @RequestBody OrderDto updatedOrderDto){
        OrderDto orderDto = orderService.update(orderId, updatedOrderDto);
        return ResponseEntity.ok(orderDto);
    }

    // Delete order by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long orderId){
        orderService.delete(orderId);
        return ResponseEntity.ok("Order deleted succesfully!");
    }
}
