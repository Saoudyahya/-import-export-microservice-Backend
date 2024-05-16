package com.example.importexportservice.service;


import com.example.importexportservice.models.OrderOperation;
import com.example.importexportservice.Repository.OrderOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderOperationService {

    private final OrderOperationRepository orderOperationRepository;

    @Autowired
    public OrderOperationService(OrderOperationRepository orderOperationRepository) {
        this.orderOperationRepository = orderOperationRepository;
    }

    public List<OrderOperation> getAllOrderOperations() {
        return orderOperationRepository.findAll();
    }

    public Optional<OrderOperation> getOrderOperationById(Long orderOperationId) {
        return orderOperationRepository.findById(orderOperationId);
    }

    public OrderOperation createOrderOperation(OrderOperation orderOperation) {
        return orderOperationRepository.save(orderOperation);
    }

    public OrderOperation updateOrderOperation(Long orderOperationId, OrderOperation updatedOrderOperation) {
        Optional<OrderOperation> optionalOrderOperation = orderOperationRepository.findById(orderOperationId);
        if (optionalOrderOperation.isPresent()) {
            OrderOperation existingOrderOperation = optionalOrderOperation.get();
            existingOrderOperation.setOrder(updatedOrderOperation.getOrder());

            existingOrderOperation.setPaid(updatedOrderOperation.isPaid());
            existingOrderOperation.setPayment_type(updatedOrderOperation.getPayment_type());

            return orderOperationRepository.save(existingOrderOperation);
        } else {
            // Handle case where order operation with given ID does not exist
            // For example, throw an exception or return null
            return null;
        }
    }

    public void deleteOrderOperation(Long orderOperationId) {
        orderOperationRepository.deleteById(orderOperationId);
    }
}