package com.example.importexportservice.controllers;

import com.example.importexportservice.Configuration.MQConfig;
import com.example.importexportservice.MessageModel.OrderMessage;
import com.example.importexportservice.MessageModel.SupplierMessage;
import com.example.importexportservice.models.Order;
import com.example.importexportservice.models.Supplier;
import com.example.importexportservice.service.OrderItemService;
import com.example.importexportservice.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    @Autowired
    private RabbitTemplate template ;

    @Autowired
    public OrderController(OrderService orderService,OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService=orderItemService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        Order newOrder= orderService.createOrder(order);
        publishMessage("CREATE", newOrder);
        return newOrder;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        publishMessage("UPDATE", updatedOrder);
        return orderService.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        Order deletedOrder = orderService.getOrderById(id).orElse(null);
        publishMessage("DELETE", deletedOrder);
        orderService.deleteOrder(id);
    }

    @DeleteMapping("/notify-product-deletion/{productId}")
    public ResponseEntity<String> deleteOrderItemsByProduct(@PathVariable Long productId) {
        orderItemService.deleteOrderItemsByProductId(productId);
        return ResponseEntity.ok("Order items for product " + productId + " deleted successfully");
    }

    private void publishMessage(String action, Order Order) {
        OrderMessage message = new OrderMessage();
        message.setAction(action);
        message.setOrderId(Order.getOrderId());
        message.setMessageId(UUID.randomUUID().toString());
                message.setMessage(" Order: " + Order.getReference()+" with id:"+ Order.getOrderId() +" type: "+ Order.getOrderType()+" have been "+ action );
        message.setMassageDate(new Date());
        template.convertAndSend(MQConfig.IMPORT_EXPORT_TOPIC_EXCHANGE, MQConfig.ROUTING_KEY, message);
    }


}