package com.example.importexportservice.controllers;

import com.example.importexportservice.models.Operation;
import com.example.importexportservice.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    // Endpoint to create a new operation
    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
        Operation savedOperation = operationService.saveOperation(operation);
        return new ResponseEntity<>(savedOperation, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all operations
    @GetMapping
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> operations = operationService.getAllOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    // Endpoint to retrieve an operation by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable("id") Long operationId) {
        Optional<Operation> operation = operationService.getOperationById(operationId);
        return operation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to delete an operation by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable("id") Long operationId) {
        operationService.deleteOperation(operationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}