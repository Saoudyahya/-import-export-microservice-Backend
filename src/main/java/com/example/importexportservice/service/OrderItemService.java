package com.example.importexportservice.service;


import com.example.importexportservice.models.OrderItem;
import com.example.importexportservice.Repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Method to save an order item
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Method to get all order items
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Method to get an order item by ID
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    // Method to update an order item
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        if (orderItemRepository.existsById(id)) {
            updatedOrderItem.setId(id);
            return orderItemRepository.save(updatedOrderItem);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
    @Transactional
    public void deleteOrderItemsByProductId(Long productId) {
        orderItemRepository.deleteByProductId(productId);
    }
}