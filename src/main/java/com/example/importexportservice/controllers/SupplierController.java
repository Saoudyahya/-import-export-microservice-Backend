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
        Supplier newSupplier=supplierService.createSupplier(supplier);
        publishMessage("CREATE", newSupplier);
        return newSupplier;
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {

        Supplier updated = supplierService.updateSupplier(id, updatedSupplier);
        publishMessage("UPDATE", updated);
        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        Supplier deletedSupplier = supplierService.getSupplierById(id).orElse(null);
        if (deletedSupplier != null) {
            supplierService.deleteSupplier(id);
            publishMessage("DELETE", deletedSupplier);
        }
    }

    @DeleteMapping("/notify-product-deletion/{productId}")
    public void handleProductDeletionNotification(@PathVariable Long productId) {
        List<Supplier> affectedSuppliers = supplierService.findSuppliersByProductId(productId);
        for (Supplier supplier : affectedSuppliers) {
            supplier.getProductIds().remove(productId);
            supplierService.createSupplier(supplier);
        }
    }

    private void publishMessage(String action, Supplier supplier) {
        SupplierMessage message = new SupplierMessage();
        message.setAction(action);
        message.setSupplierId(supplier.getSupplierId());
        message.setSupplierName(supplier.getSupplierName());
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessage(" supplier: " + supplier.getSupplierName()+" with id:"+supplier.getSupplierId()+" from: "+ supplier.getCountry()+ " / " +supplier.getRegion()+" have been "+ action ); // Set the Message field based on the action
        message.setMassageDate(new Date());
        template.convertAndSend(MQConfig.IMPORT_EXPORT_TOPIC_EXCHANGE, MQConfig.ROUTING_KEY, message);
    }




}