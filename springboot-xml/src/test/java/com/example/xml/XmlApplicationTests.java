package com.example.xml;

import com.example.xml.controller.XmlController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class XmlApplicationTests {

    @Autowired
    private XmlController xmlController;

    @Test
    void contextLoads() {
        assertThat(xmlController).isNotNull();
    }

}
