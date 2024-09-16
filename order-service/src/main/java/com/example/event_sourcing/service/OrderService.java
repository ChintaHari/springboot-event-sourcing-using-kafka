package com.example.event_sourcing.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.event_sourcing.dto.enums.OrderStatus;
import com.example.event_sourcing.dto.request.OrderRequest;
import com.example.event_sourcing.dto.response.OrderResponse;
import com.example.event_sourcing.entity.OrderEvent;
import com.example.event_sourcing.publisher.OrderEventKafkaPublisher;
import com.example.event_sourcing.repository.OrderEventRepository;

@Service
public class OrderService {

    @Autowired
    private OrderEventKafkaPublisher orderEventKafkaPublisher;

    @Autowired
    private OrderEventRepository orderEventRepository;
    

    public OrderResponse createOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString().split("-")[0];
        orderRequest.setOrderId(orderId);
        OrderEvent orderEvent = new OrderEvent(orderId, OrderStatus.CREATED, "Order Created", LocalDateTime.now());
        saveAndPublishOrderEvent(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CREATED);
    }

    public OrderResponse confirmOrder(String orderId) {
        OrderEvent orderEvent = new OrderEvent(orderId, OrderStatus.CONFIRMED, "Order Confirmed", LocalDateTime.now());
        saveAndPublishOrderEvent(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CONFIRMED);
        
    }

    public void saveAndPublishOrderEvent(OrderEvent orderEvent) {
        orderEventRepository.save(orderEvent);
        orderEventKafkaPublisher.sendOrderEvent(orderEvent);
    }
}

