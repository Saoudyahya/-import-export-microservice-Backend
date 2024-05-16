package com.example.importexportservice.Repository;

import com.example.importexportservice.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.importexportservice.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBySupplier(Supplier supplier);
}
