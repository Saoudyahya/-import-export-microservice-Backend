package com.example.importexportservice.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Long operationId;

    @Column(name = "operation_Reference")
    private String operationReference;

    @Column(name = "operation_state")
    private String operationState;

    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinColumn(name = "operation_id", referencedColumnName = "operation_id")
    private List<OrderOperation> orderOperations;


    public Operation(Long operationId, String operationReference, String operationState, List<OrderOperation> orderOperations) {
        this.operationId = operationId;
        this.operationReference = operationReference;
        this.operationState = operationState;
        this.orderOperations = orderOperations;
    }

    public Operation() {

    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getOperationReference() {
        return operationReference;
    }

    public void setOperationReference(String operationReference) {
        this.operationReference = operationReference;
    }

    public String getOperationState() {
        return operationState;
    }

    public void setOperationState(String operationState) {
        this.operationState = operationState;
    }

    public List<OrderOperation> getOrderOperations() {
        return orderOperations;
    }

    public void setOrderOperations(List<OrderOperation> orderOperations) {
        this.orderOperations = orderOperations;
    }
}
