package com.example.importexportservice.service;

import com.example.importexportservice.models.Order;
import com.example.importexportservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            // Update fields of existingOrder with updatedOrder
            // Assuming you have getters and setters in Order class
            existingOrder.setOrderType(updatedOrder.getOrderType());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            // Save the updated order
            return orderRepository.save(existingOrder);
        } else {
            // Handle case where order with given ID does not exist
            // For example, throw an exception or return null
            return null;
        }
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}