package com.example.email.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
public class Email {
    @Schema(example = "lahoti.ashish20@gmail.com")
    String to;
    @Schema(example = "lahoti.ashish20@gmail.com")
    String from;
    @Schema(example = "Welcome Email from CodingNConcepts")
    String subject;
    @Schema(example = "Thank you for subscribing to our channel.")
    String text;
    @Schema(example = "welcome-email.html")
    String template;
    @Schema(example = "{\n" +
        "\"name\": \"Ashish\",\n" +
        "\"subscriptionDate\": \"28-12-2012\",\n" +
        " \"technologies\": [\"java\", \"javascript\"]\n" +
        "}")
    Map<String, Object> properties;
}
