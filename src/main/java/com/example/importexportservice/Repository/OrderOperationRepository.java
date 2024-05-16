package com.example.importexportservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.importexportservice.models.OrderOperation;

public interface OrderOperationRepository extends JpaRepository<OrderOperation, Long> {
    // Add custom methods if needed
}
