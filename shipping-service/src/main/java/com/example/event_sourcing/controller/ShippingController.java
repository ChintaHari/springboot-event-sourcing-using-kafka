package com.example.event_sourcing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.event_sourcing.service.ShippingEventService;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingEventService shippingEventService;

    @PostMapping("/ship/{orderId}")
    public ResponseEntity<String> shipOrder(@PathVariable String orderId) {
        shippingEventService.shipOrder(orderId);
        return ResponseEntity.ok("Order shipped successfully.");
    }

    @PostMapping("/deliver/{orderId}")
    public ResponseEntity<String> deliverOrder(@PathVariable String orderId) {
        shippingEventService.deliverOrder(orderId);
        return ResponseEntity.ok("Order delivered successfully.");
    }
}
