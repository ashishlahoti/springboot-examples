package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@JsonIgnoreProperties({"$$beanFactory"})
@Data
@Slf4j
public class CourseConfig {

    @Value("${course.title}")
    private String title;

    @Value("${course.duration}")
    private int duration;

    @Value("${course.rating}")
    private float rating;

    @Value("${course.page_views}")
    private double pageViews;

    @Value("${course.trending}")
    private boolean isTrending;

    @Value("${course.title} (${course.rating})")
    private String titleAndRating;

    @Value("${course.title} - ${course.duration} min")
    private String titleAndDuration;

    // Default Value
    @Value("${course.review: No Reviews Yet}")
    private String review;

    // Inject Date with given format using SpEL expression
    @Value("#{new java.text.SimpleDateFormat('yyyyMMdd').parse('${course.created_date}')}")
    private Date createdDate;

    // Inject LocalDate with ISO_DATE format using SpEL expression
    @Value("#{T(java.time.LocalDate).parse('${course.updated_date}')}")
    private LocalDate updatedDate;

    // Inject LocalDateTime with ISO_LOCAL_DATE_TIME format using SpEL expression
    @Value("#{T(java.time.LocalDateTime).parse('${course.last_access}')}")
    private LocalDateTime lastAccess;

    // Comma separated property values auto initialize a List
    //@Value("${course.tags}")
    @Value("#{'${course.tags}'.split(',')}")
    private List<String> tags;

    @Value("#{'${course.tags}'.split(',')[0]}")
    private String firstTag;

    // SpEL expression used to initialize a Map
    @Value("#{${course.keyword_count}}")
    private Map<String, Integer> keywordCountMap;

    @Value("#{${course.keyword_count}.keyword1}")
    private Integer firstKeywordCount;

    @Value("#{${course.keyword_count}['keyword2']}")
    private Integer secondKeywordCount;

    @Value("#{${course.keyword_count}.?[value > '50']}")
    private Map<String, Integer> keywordCountMapFiltered;

    @Value("#{${unknownMap : {key1: '1', key2: '2'}}}")
    private Map<String, Integer> unknownMap;

    @Bean
    @ConfigurationProperties(prefix = "course.label")
    public Map<String, String> getLabels() {
        return new HashMap<>();
    }

    @Value("#{${course.keyword_count}['unknownKey'] ?: 100}")
    private Integer unknownKeyWithDefaultValue;

    @Value("#{systemProperties}")
    private Map<String, String> systemPropertiesMap;

    @Value("${file.encoding}")
    private String fileEncoding;

    @Value("#{systemProperties['java.home']}")
    private String javaVersion;

    @Value("Test")
    public void printValues(String a, String b) {
        log.info(a + " & " + b); // Test & Another Test
    }

    @Value("Test")
    public void printAnotherValues(String a, @Value("Another Test") String b) {
        log.info(a + " & " + b); // Test & Another Test
    }

}
