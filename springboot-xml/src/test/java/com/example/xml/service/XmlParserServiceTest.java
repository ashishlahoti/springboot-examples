package com.example.xml.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class XmlParserServiceTest {

    @Autowired
    private XmlParserService xmlParserService;

    @Test
    public void toJson_whenValidClassAndXML_returnJson() throws ClassNotFoundException, IOException {
        String xml = read("data/accountSummary.xml");
        JsonNode json = xmlParserService.toJson(Class.forName("com.example.jaxb.AccountSummary"), xml);
        assertThat(json.asText()).isNotNull();
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }

}
