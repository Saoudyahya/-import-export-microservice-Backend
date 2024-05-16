package com.example.importexportservice.controllers;

import com.example.importexportservice.Configuration.MQConfig;
import com.example.importexportservice.MessageModel.SupplierMessage;
import com.example.importexportservice.models.Supplier;
import com.example.importexportservice.service.SupplierService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    @Autowired
    private RabbitTemplate template ;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {
        return supplierService.updateSupplier(id, updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }

    @DeleteMapping("/notify-product-deletion/{productId}")
    public void handleProductDeletionNotification(@PathVariable Long productId) {
        List<Supplier> affectedSuppliers = supplierService.findSuppliersByProductId(productId);
        for (Supplier supplier : affectedSuppliers) {
            supplier.getProductIds().remove(productId);
            supplierService.createSupplier(supplier);
        }
    }

    @PostMapping("/Message")
    public String publishMessage(@RequestBody SupplierMessage message){
        message.setMessageId((UUID.randomUUID().toString()));
        message.setMassageDate(new Date());
        template.convertAndSend(MQConfig.IMPORT_EXPORT_TOPIC_EXCHANGE,MQConfig.ROUTING_KEY,message);

        return "Message published";
    }

}