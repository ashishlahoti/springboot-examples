package com.example.demo.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

public class CustomServletConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/myapp");
    }

    /* For Spring Boot 1.x
    @Bean
    public EmbeddedServletContainerCustomizer<ConfigurableEmbeddedServletContainer> embeddedServletContainerCustomizer() {
        return container -> container.setContextPath("/myapp");
    }
    */
}
