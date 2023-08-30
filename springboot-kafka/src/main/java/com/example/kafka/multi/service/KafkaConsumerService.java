package com.example.kafka.multi.service;

public interface KafkaConsumerService {

    void receive(String message);
}