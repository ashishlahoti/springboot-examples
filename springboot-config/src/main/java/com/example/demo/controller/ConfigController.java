package com.example.demo.controller;

import com.example.demo.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private InlineConfig inlineConfig;

    @Autowired
    private CourseConfig courseConfig;

    @Autowired
    private PersonConfig personConfig;

    @Autowired
    private ExpressionConfig expressionConfig;

    @GetMapping("/inline")
    private InlineConfig getInlineConfig() {
        System.out.println(inlineConfig);
        return inlineConfig;
    }

    @GetMapping("/course")
    private CourseConfig getCourseConfig() {
        System.out.println(courseConfig);
        return courseConfig;
    }

    @GetMapping("/person")
    private PersonConfig getPersonConfig() {
        System.out.println(personConfig);
        return personConfig;
    }

    @GetMapping("/expression")
    private ExpressionConfig getListConfig() {
        System.out.println(expressionConfig);
        return expressionConfig;
    }
}
