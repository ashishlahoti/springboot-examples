package com.example.kafka.multi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaMultipleConsumerConfig {

  private final KafkaCustomProperties kafkaCustomProperties;

  @Bean
  @Qualifier("consumer1")
  public ConcurrentKafkaListenerContainerFactory<String, String> consumer1KafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory("consumer1"));
    return factory;
  }

  @Bean
  @Qualifier("consumer2")
  public ConcurrentKafkaListenerContainerFactory<String, String> consumer2KafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory("consumer2"));
    return factory;
  }

  private ConsumerFactory<String, Object> consumerFactory(String consumerName) {
    Map<String, Object> properties = new HashMap<>(kafkaCustomProperties.buildCommonProperties());
    if (nonNull(kafkaCustomProperties.getConsumer())) {
      KafkaProperties.Consumer consumerProperties = kafkaCustomProperties.getConsumer().get(consumerName);
      if (nonNull(consumerProperties)) {
        properties.putAll(consumerProperties.buildProperties());
      }
    }
    log.info("Kafka Consumer '{}' properties: {}", consumerName, properties);
    return new DefaultKafkaConsumerFactory<>(properties);
  }
}
