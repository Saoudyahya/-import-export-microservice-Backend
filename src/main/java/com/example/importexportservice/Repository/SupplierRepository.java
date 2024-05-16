package com.example.importexportservice.Repository;

import com.example.importexportservice.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByProductIdsContaining(Long productId);
}
