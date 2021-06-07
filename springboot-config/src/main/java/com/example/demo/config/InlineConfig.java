package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
@Data
@JsonIgnoreProperties({"$$beanFactory"})
public class InlineConfig {

    @Value("How to use @Value Annotation with inline values")
    private String title;

    @Value("30")
    private Integer duration;

    @Value("4.5")
    private Float rating;

    @Value("1e+10")
    private Double pageViews;

    @Value("true")
    private Boolean isTrending;

    @Value("Spring, Spring Boot, Annotation")
    private List<String> tags;

    // SpEL expression used to initialize a Map
    @Value("#{{'keyword1': '12', 'keyword2': '44', 'keyword3': '85', 'keyword4': '100'}}")
    private Map<String, Integer> keywordCountMap;

    // Inject Date with given format using SpEL expression
    @Value("#{new java.text.SimpleDateFormat('yyyyMMdd').parse('20210530')}")
    private Date createdDate;

    // Inject LocalDate with ISO_DATE format using SpEL expression
    @Value("#{T(java.time.LocalDate).parse('2021-05-31')}")
    private LocalDate updatedDate;

    // Inject LocalDateTime with ISO_LOCAL_DATE_TIME format using SpEL expression
    @Value("#{T(java.time.LocalDateTime).parse('2015-08-04T10:11:30')}")
    private LocalDateTime lastAccess;

}
