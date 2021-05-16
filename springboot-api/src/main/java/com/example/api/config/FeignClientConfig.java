package com.example.api.config;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {

    /**
     * Enable this bean if you want to add headers in HTTP request
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("header_1", "value_1");
            requestTemplate.header("header_2", "value_2");
            requestTemplate.header("header_3", "value_3");
        };
    }

    /**
     * Enable this bean if you want to add basic Authorization header
     * for e.g. Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("username", "password");
    }
}
