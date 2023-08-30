package com.example.kafka.single.config;

import com.example.kafka.single.service.CryptoService;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private ConsumerFactory<Integer, String> consumerFactory;

    @Autowired
    private CryptoService cryptoService;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfig()));
        return factory;
    }

    private Map<String, Object> consumerConfig() {
        Map<String, Object> consumerConfig = new HashMap<>(consumerFactory.getConfigurationProperties());
        decryptAndAddToConsumerConfig(consumerConfig, SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG);
        decryptAndAddToConsumerConfig(consumerConfig, SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG);
        decryptAndAddToConsumerConfig(consumerConfig, SslConfigs.SSL_KEY_PASSWORD_CONFIG);
        return consumerConfig;
    }

    private void decryptAndAddToConsumerConfig(Map<String, Object> config, String property) {
        config.compute(property, (k, v) -> cryptoService.decrypt((String) v));
    }
}
