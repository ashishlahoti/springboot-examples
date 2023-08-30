package com.example.kafka.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaMultiApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaMultiApplication.class, "--spring.profiles.active=multi");
  }

}
