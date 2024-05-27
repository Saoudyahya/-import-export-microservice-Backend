package com.example.importexportservice.service;


import com.example.importexportservice.models.Operation;
import com.example.importexportservice.Repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    private final OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    // Method to save an operation
    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    // Method to retrieve all operations
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }


    public Operation updateOperation(Long id, Operation operationDetails)  {
        Optional<Operation> optionalOperation = operationRepository.findById(id);

        if (optionalOperation.isPresent()) {
            Operation operation = optionalOperation.get();
            operation.setOperationReference(operationDetails.getOperationReference());
            operation.setOperationState(operationDetails.getOperationState());
            operation.setOrderOperations(operationDetails.getOrderOperations());

            return operationRepository.save(operation);
        } else {
            return null;
        }

    }


    public Optional<Operation> getOperationById(Long operationId) {
        return operationRepository.findById(operationId);
    }

    // Method to delete an operation by its ID
    public void deleteOperation(Long operationId) {
        operationRepository.deleteById(operationId);
    }


}