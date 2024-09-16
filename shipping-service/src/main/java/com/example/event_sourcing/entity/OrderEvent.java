package com.example.event_sourcing.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.event_sourcing.dto.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "OrderEvents")
public class OrderEvent {
    @Id
    private String id;
    private String orderId;
    private OrderStatus status;  // SHIPPED, DELIVERED
    private String details;
    private LocalDateTime eventTimestamp;

    public OrderEvent(String orderId, OrderStatus status, String details, LocalDateTime eventTimestamp) {
        this.orderId = orderId;
        this.status = status;
        this.details = details;
        this.eventTimestamp = eventTimestamp;
    }
}
