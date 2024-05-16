package com.example.importexportservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.importexportservice.models.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    // Add custom methods if needed
}
