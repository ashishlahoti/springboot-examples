package com.example.xml.controller;

import com.example.xml.service.XmlParserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XmlController {

    private final XmlParserService xmlParserService;

    @PostMapping("/{entityType}/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Object parseXmlToJson(@PathVariable String entityType, @RequestBody String xml) throws ClassNotFoundException, JsonProcessingException {
        return xmlParserService.toJson(Class.forName("com.example.jaxb." + entityType), xml);
    }

}
