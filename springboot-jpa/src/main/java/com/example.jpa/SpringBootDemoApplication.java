package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootDemoApplication {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
	
	/*
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootDemoApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
        app.setAddCommandLineProperties(false);
        app.run(args);
	}
	
    */
	
	
}
