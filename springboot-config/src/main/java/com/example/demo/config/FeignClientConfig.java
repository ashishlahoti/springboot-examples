package com.example.demo.config;

import okhttp3.OkHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {


    @Bean
    public CloseableHttpClient feignClient() {
        return HttpClients.createDefault();
    }

    /*
    @Bean
    public OkHttpClient feignClient() {
        return new OkHttpClient();
    }
     */

}
