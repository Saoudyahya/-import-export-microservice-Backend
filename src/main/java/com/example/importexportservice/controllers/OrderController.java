package com.example.importexportservice.controllers;

import com.example.importexportservice.models.Order;
import com.example.importexportservice.service.OrderItemService;
import com.example.importexportservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderController(OrderService orderService,OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService=orderItemService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @DeleteMapping("/notify-product-deletion/{productId}")
    public ResponseEntity<String> deleteOrderItemsByProduct(@PathVariable Long productId) {
        orderItemService.deleteOrderItemsByProductId(productId);
        return ResponseEntity.ok("Order items for product " + productId + " deleted successfully");
    }


}