package com.example.event_sourcing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.event_sourcing.entity.OrderEvent;

public interface OrderEventRepository extends MongoRepository<OrderEvent,String> {
}
