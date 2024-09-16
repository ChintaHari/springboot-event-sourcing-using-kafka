package com.example.event_sourcing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    
    @Value("${order.event.topicName}")
    private String orderEventTopicName;

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(orderEventTopicName, 1, (short) 1);
    }
}
