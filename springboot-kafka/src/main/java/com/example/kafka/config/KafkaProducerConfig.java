package com.example.kafka.config;

import com.example.kafka.service.CryptoService;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private ProducerFactory<Integer, String> producerFactory;

    @Autowired
    private CryptoService cryptoService;

    public Map<String, Object> producerConfig() {
        Map<String, Object> producerConfig = new HashMap<>(producerFactory.getConfigurationProperties());
        decryptAndAddToConsumerConfig(producerConfig, SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG);
        decryptAndAddToConsumerConfig(producerConfig, SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG);
        decryptAndAddToConsumerConfig(producerConfig, SslConfigs.SSL_KEY_PASSWORD_CONFIG);
        return producerConfig;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig()));
    }

    private void decryptAndAddToConsumerConfig(Map<String, Object> config, String property) {
        config.compute(property, (k, v) -> cryptoService.decrypt((String) v));
    }
}