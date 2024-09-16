package com.example.event_sourcing.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.event_sourcing.dto.enums.OrderStatus;
import com.example.event_sourcing.entity.OrderEvent;
import com.example.event_sourcing.repository.OrderEventRepository;

@Service
public class ShippingEventService {

    @Autowired
    private OrderEventRepository repository;


    @KafkaListener(topics = "order-events", groupId = "shipping-service")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        if (orderEvent.getStatus().equals(OrderStatus.CONFIRMED)) {
            shipOrder(orderEvent.getOrderId());
        }
    }

    public void shipOrder(String orderId) {
        OrderEvent orderEvent = new OrderEvent(orderId, OrderStatus.SHIPPED, "Order Shipped", LocalDateTime.now());
        repository.save(orderEvent);
    }

    public void deliverOrder(String orderId) {
        OrderEvent orderEvent = new OrderEvent(orderId, OrderStatus.DELIVERED, "Order delivered", LocalDateTime.now());
        repository.save(orderEvent);
    }
}
