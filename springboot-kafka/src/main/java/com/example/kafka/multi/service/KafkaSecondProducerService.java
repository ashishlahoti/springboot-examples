package com.example.kafka.multi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaSecondProducerService {
  @Qualifier("producer2")
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value("${kafka.producer.producer2.topic}")
  private String topic;

  public void send(String message) {
    log.info("sending message from second producer: {}", message);
    kafkaTemplate.send(topic, message);
  }
}
