package com.example.kafka.single.service;

public interface KafkaConsumerService {

    void receive(String message);
}