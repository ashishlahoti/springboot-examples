package com.example.kafka.multi.service;

import com.example.kafka.single.service.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaFirstConsumerServiceImpl implements KafkaConsumerService {

    @KafkaListener(topics = {"${kafka.consumer.consumer1.topic}"}, groupId = "${kafka.consumer.consumer1.group-id}", containerFactory = "consumer1ContainerFactory")
    public void receive(@Payload String message) {
        log.info("message received in consumer1: {}", message);
    }
}