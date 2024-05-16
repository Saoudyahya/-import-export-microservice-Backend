package com.example.importexportservice.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_operation")
public class OrderOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_operation_id")
    private Long orderOperationId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;




    @Column(name = "is_paid")
        private boolean isPaid;

    @Column(name = "Payment_type")
    private String Payment_type;

    public OrderOperation(Long orderOperationId, Order order, boolean isPaid, String payment_type) {
        this.orderOperationId = orderOperationId;
        this.order = order;
        this.isPaid = isPaid;
        this.Payment_type = payment_type;
    }


    public OrderOperation() {

    }

    public Long getOrderOperationId() {
        return orderOperationId;
    }

    public void setOrderOperationId(Long orderOperationId) {
        this.orderOperationId = orderOperationId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }



    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getPayment_type() {
        return Payment_type;
    }

    public void setPayment_type(String payment_type) {
        Payment_type = payment_type;
    }
}
