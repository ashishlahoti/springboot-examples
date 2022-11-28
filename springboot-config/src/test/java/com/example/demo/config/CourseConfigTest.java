package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

@SpringBootTest
public class CourseConfigTest {

    @Autowired
    CourseConfig courseConfig;

    @Test
    public void testCourseConfigProperties(){
        assertThat(courseConfig.getTitle()).isEqualTo("How to use Spring @Value annotation");
        assertThat(courseConfig.getDuration()).isEqualTo(30);
        assertThat(courseConfig.getRating()).isEqualTo(4.5f);
        assertThat(courseConfig.isTrending()).isTrue();
        assertThat(courseConfig.getTags()).contains("Spring", atIndex(0));
        assertThat(courseConfig.getTags()).contains("Spring Boot", atIndex(1));
        assertThat(courseConfig.getTags()).contains("Annotation", atIndex(2));
        assertThat(courseConfig.getKeywordCountMap()).containsEntry("keyword1", 12);
        assertThat(courseConfig.getKeywordCountMap()).containsEntry("keyword2", 44);
        assertThat(courseConfig.getLabels()).containsEntry("NAME", "Course Title");
        assertThat(courseConfig.getLabels()).containsEntry("DESCRIPTION", "Course Description");

    }
}
