package com.example.event_sourcing.dto.response;

import com.example.event_sourcing.dto.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String orderId;
    private OrderStatus status;
}
