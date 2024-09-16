# Event Sourcing Microservices Demonstration

## Overview

This project demonstrates the implementation of an event sourcing strategy using Spring Boot microservices. The example involves two services: `order-service` and `shipping-service`, which manage the lifecycle of an order from creation through shipping and delivery.

## What is Event Sourcing?

Event sourcing is an architectural pattern where all changes to application state are stored as a sequence of events. Instead of only saving the current state of data in a domain, event sourcing also saves each state change as an event which can be replayed to reconstruct past states or the entire entity.

## Importance of Event Sourcing

Event sourcing ensures that all changes to the application state are captured in an event log, which acts as a record of all actions. This allows for accurate auditing, troubleshooting, and replaying of events to restore or predict system states.

## Challenges Without Event Sourcing

Traditional systems often update the database with the latest status of entities such as orders, losing the history of previous states in the process. For instance, if an order moves through states `CREATED`, `CONFIRMED`, `SHIPPED`, and `DELIVERED`, only the current state is retained. If a need arises to analyze the transition or revert to a previous state due to an error or complaint, this information is unavailable, impacting business operations and customer satisfaction.

## Why Choose Event Sourcing?

Event sourcing addresses these challenges by maintaining a log of all state changes. This not only allows for recovering previous states but also aids in debugging and understanding the sequence of actions that led to a particular state.

## Our Demo Application Design

![OurDemoApplicationDesign](link-to-image)

## Creating Order Microservice (`order-service`)

### Overview
The `order-service` handles the creation and confirmation of orders. It publishes events to a Kafka topic that the shipping service can consume.

### Dependencies
- Spring Boot Web
- Spring Boot Data MongoDB
- Spring Kafka
- Lombok
- Spring Boot Devtools

### Configuration Highlights
```yaml
spring:
  application:
    name: order-service
  kafka:
    template:
      default-topic: order-events
server:
  port: 8081
```

## APIs and Their Functions
- **POST `/orders/create`**: Creates a new order and publishes an event.
- **PUT `/orders/confirm/{orderId}`**: Confirms an order and publishes an event.

## Significance of Kafka as a Publisher
Kafka is used to publish order events which the shipping service listens to. This decouples the order processing and shipping processes and allows the shipping service to react to order confirmations.

## Creating Shipping Microservice (`shipping-service`)

### Overview
The `shipping-service` listens for confirmed orders from the `order-service` and handles the shipping and delivery processes.

### Dependencies
Identical to `order-service`, focusing on Kafka listeners and MongoDB for data persistence.

### Configuration Highlights
```yaml
spring:
  kafka:
    consumer:
      group-id: shipping-service
server:
  port: 8082
```

## APIs and Their Functions
- **POST `/shipping/ship/{orderId}`**: Automatically triggered by Kafka listener on order confirmation to ship an order.
- **POST `/shipping/deliver/{orderId}`**: Manually triggers the delivery of an order.

## Significance of Kafka as a Listener
Kafka is crucial for enabling the `shipping-service` to listen for events published by `order-service`, ensuring that shipping actions are based on real-time data and are fully automated.

