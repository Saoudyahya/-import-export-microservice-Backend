package com.example.importexportservice.models;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;


    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    @OneToMany(cascade = CascadeType.REMOVE ,fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "order_Type")
    private OrderType orderType;

    public Order(Long orderId, Supplier supplier, List<OrderItem> orderItems, OrderType orderType, Date orderDate, String reference, int total_price) {
        this.orderId = orderId;
        this.supplier = supplier;
        this.orderItems= orderItems;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.reference = reference;
        Total_price = total_price;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;




    @Column(name = "order_reference")
    private String reference;

    @Column(name = "order_Total_price")
    private int Total_price;

    public Order() {
    }





    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }



    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public int getTotal_price() {
        return Total_price;
    }

    public void setTotal_price(int total_price) {
        Total_price = total_price;
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


}
