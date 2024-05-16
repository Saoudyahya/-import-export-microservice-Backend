package com.example.importexportservice.controllers;

import com.example.importexportservice.models.OrderOperation;
import com.example.importexportservice.service.OrderOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderOperations")
public class OrderOperationController {

    private final OrderOperationService orderOperationService;

    @Autowired
    public OrderOperationController(OrderOperationService orderOperationService) {
        this.orderOperationService = orderOperationService;
    }

    @GetMapping
    public List<OrderOperation> getAllOrderOperations() {
        return orderOperationService.getAllOrderOperations();
    }

    @GetMapping("/{id}")
    public Optional<OrderOperation> getOrderOperationById(@PathVariable Long id) {
        return orderOperationService.getOrderOperationById(id);
    }

    @PostMapping
    public OrderOperation createOrderOperation(@RequestBody OrderOperation orderOperation) {
        return orderOperationService.createOrderOperation(orderOperation);
    }

    @PutMapping("/{id}")
    public OrderOperation updateOrderOperation(@PathVariable Long id, @RequestBody OrderOperation updatedOrderOperation) {
        return orderOperationService.updateOrderOperation(id, updatedOrderOperation);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderOperation(@PathVariable Long id) {
        orderOperationService.deleteOrderOperation(id);
    }



}