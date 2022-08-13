package com.example.xml.controller;

import com.example.xml.service.XmlParserService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class XmlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private XmlParserService xmlParserService;

    @Test
    public void parseXmlToJson_whenValidRequest_returnsValidResponse() throws Exception {
        String xml = read("data/accountSummary.xml");
        mockMvc.perform(post("/xml/AccountSummary/json").content(xml))
            .andExpect(status().is2xxSuccessful())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.currency", equalTo("USD")))
            .andExpect(jsonPath("$.balance", equalTo(2703.35)))
            .andExpect(jsonPath("$.interest.value", equalTo(27.55)))
            .andExpect(jsonPath("$.interest.rounding", equalTo("half up")))
            .andExpect(jsonPath("$.version", equalTo("1.0")));
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }

}