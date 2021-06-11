package com.example.demo.controller;

import com.example.demo.condition.CombinedConditionsWithAnyMatch;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/condition")
@Conditional(CombinedConditionsWithAnyMatch.class)
public class ConditionalController {

    @GetMapping
    public String print() {
        return "Custom condition is matched";
    }
}
