package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cloud.util.ConditionalOnBootstrapDisabled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class CustomErrorController implements ErrorController {
    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(PATH)
    public Map<String, Object> error(WebRequest request, HttpServletResponse response) {
        return getErrorAttributes(request, true);
    }

    private Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults()
                .including(ErrorAttributeOptions.Include.MESSAGE)
                .including(ErrorAttributeOptions.Include.EXCEPTION)
                .including(ErrorAttributeOptions.Include.BINDING_ERRORS);
        if(includeStackTrace){
            options = options.including(ErrorAttributeOptions.Include.STACK_TRACE);
        }
        return this.errorAttributes.getErrorAttributes(request, options);
    }
}
