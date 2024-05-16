package com.example.importexportservice.models;
import jakarta.persistence.*;
@Entity
@Table(name = "order_item")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;



    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;
    @Column(name = "Order_price")
    private int Order_price;

    public OrderItem() {
    }


    public OrderItem(Long id, Order order, Long productId, int quantity, int order_price) {
        this.id = id;

        this.productId = productId;
        this.quantity = quantity;
        this.Order_price = order_price;
    }

    public int getOrder_price() {
        return Order_price;
    }

    public void setOrder_price(int order_price) {
        Order_price = order_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}