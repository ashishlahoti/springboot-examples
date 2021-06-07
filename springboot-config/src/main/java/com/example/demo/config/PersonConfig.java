package com.example.demo.config;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("person")
@Data
@JsonIgnoreProperties({"$$beanFactory"})
public class PersonConfig {

    private String name;
    private String occupation;
    private int age;
    private float gpa;
    private double favNum;
    private boolean male;
    private String birthday;
    private String flaws;
    private String[] hobbies;
    private List<String> movies;
    private Map<String, Integer> assets;
    private Map<String, Map<String, String>> size;
    private List<Friend> friends;
    private String description;
    private String signature;
}

@Data
class Friend {
    private String name;
    private int age;
}
