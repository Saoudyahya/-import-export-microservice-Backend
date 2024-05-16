package com.example.importexportservice.service;

import com.example.importexportservice.Repository.OrderRepository;
import com.example.importexportservice.models.Order;
import com.example.importexportservice.models.Supplier;
import com.example.importexportservice.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    private  final OrderRepository OrderRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository,OrderRepository OrderRepository) {

        this.supplierRepository = supplierRepository;
        this.OrderRepository=OrderRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long supplierId, Supplier updatedSupplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (optionalSupplier.isPresent()) {
            Supplier existingSupplier = optionalSupplier.get();
            existingSupplier.setSupplierName(updatedSupplier.getSupplierName());
            existingSupplier.setCountry(updatedSupplier.getCountry());

            return supplierRepository.save(existingSupplier);
        } else {

            return null;
        }
    }

    public List<Supplier> findSuppliersByProductId(Long productId) {
        return supplierRepository.findByProductIdsContaining(productId);
    }


        public void deleteSupplier(Long supplierId) {
            Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
            if (optionalSupplier.isPresent()) {
                Supplier supplier = optionalSupplier.get();
                List<Order> orders = OrderRepository.findBySupplier(supplier);
                OrderRepository.deleteAll(orders);
                supplierRepository.delete(supplier);
            }
        }
}

