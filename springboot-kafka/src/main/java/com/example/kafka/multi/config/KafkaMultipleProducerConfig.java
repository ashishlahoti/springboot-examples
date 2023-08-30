package com.example.kafka.multi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaMultipleProducerConfig {

  private final KafkaCustomProperties kafkaCustomProperties;

  @Bean
  @Qualifier("producer1")
  public KafkaTemplate<String, Object> producer1KafkaTemplate() {
    return new KafkaTemplate<>(producerFactory("producer1"));
  }

  @Bean
  @Qualifier("producer2")
  public KafkaTemplate<String, Object> producer2KafkaTemplate() {
    return new KafkaTemplate<>(producerFactory("producer2"));
  }

  private ProducerFactory<String, Object> producerFactory(String producerName) {
    Map<String, Object> properties = new HashMap<>(kafkaCustomProperties.buildCommonProperties());
    if (nonNull(kafkaCustomProperties.getProducer())) {
      KafkaProperties.Producer producerProperties = kafkaCustomProperties.getProducer().get(producerName);
      if (nonNull(producerProperties)) {
        properties.putAll(producerProperties.buildProperties());
      }
    }
    log.info("Kafka Producer '{}' properties: {}", producerName, properties);
    return new DefaultKafkaProducerFactory<>(properties);
  }
}
