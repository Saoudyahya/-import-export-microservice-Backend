package com.example.importexportservice.Repository;

import com.example.importexportservice.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByProductId(Long productId);
}
