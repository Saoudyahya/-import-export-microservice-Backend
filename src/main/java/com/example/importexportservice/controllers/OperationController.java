package com.example.importexportservice.controllers;

import com.example.importexportservice.Configuration.MQConfig;
import com.example.importexportservice.MessageModel.OperationMessage;

import com.example.importexportservice.models.Operation;
import com.example.importexportservice.service.OperationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationService operationService;
    @Autowired
    private RabbitTemplate template ;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    // Endpoint to create a new operation
    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation) {
        Operation savedOperation = operationService.saveOperation(operation);
        publishMessage("CREATE", savedOperation);
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
        Optional<Operation> operation = operationService.getOperationById(operationId);
        operation.ifPresent(operation1 -> publishMessage("DELETE", operation1));
        operationService.deleteOperation(operationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperation(@PathVariable(value = "id") Long operationId,
                                                     @RequestBody Operation operationDetails) {
        publishMessage("UPDATE", operationDetails);
        return ResponseEntity.ok(operationService.updateOperation(operationId, operationDetails));
    }
    private void publishMessage(String action, Operation operation) {
        OperationMessage message = new OperationMessage();
        message.setAction(action);
        message.setOperationId(operation.getOperationId());
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessage(" operation: " + operation.getOperationReference()+" with id:"+operation.getOperationId()+" state: "+ operation.getOperationState()+" have been "+ action ); // Set the Message field based on the action
        message.setMassageDate(new Date());
        template.convertAndSend(MQConfig.IMPORT_EXPORT_TOPIC_EXCHANGE, MQConfig.ROUTING_KEY, message);
    }


}