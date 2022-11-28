package com.example.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mockerror")
public class MockErrorController {

    @GetMapping("/5xx")
    public void throwInternalServerError(){
        throw new RuntimeException("Mocked Runtime Exception");
    }

    @PostMapping("/4xx")
    public void methodNotAllowed(){

    }
}
