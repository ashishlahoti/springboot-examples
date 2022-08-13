package com.example.xml.service;

import com.example.xml.adapter.EnumValueDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.jvnet.jaxb2_commons.lang.EnumValue;
import org.springframework.stereotype.Service;

@Service
public class XmlParserService {

    private static final XmlMapper xmlMapper = XmlMapper.builder()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .addModule(new JaxbAnnotationModule())
        .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(EnumValue.class, new EnumValueDeserializer());
        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public JsonNode toJson(Class classType, String xml) throws JsonProcessingException {
        Object obj = xmlMapper.reader().forType(classType).readValue(xml);
        return objectMapper.convertValue(obj, JsonNode.class);
    }

}
