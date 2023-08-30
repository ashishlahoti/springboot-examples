package com.example.kafka.multi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaFirstProducerService implements KafkaProducerService {
  @Qualifier("producer1")
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value("${kafka.producer.producer1.topic}")
  private String topic;

  @Override
  public void send(String message) {
    log.info("sending message from first producer: {}", message);
    kafkaTemplate.send(topic, message);
  }
}
