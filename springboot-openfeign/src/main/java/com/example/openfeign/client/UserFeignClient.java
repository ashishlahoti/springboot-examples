package com.example.openfeign.client;

import com.example.openfeign.config.ApacheHttp5FeignSslClientConfig;
import com.example.openfeign.model.UserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userFeignClient",
    url = "${client.api.baseUrl}",
    configuration = ApacheHttp5FeignSslClientConfig.class)
public interface UserFeignClient {

    @GetMapping("/api/users/{userId}")
    UserData getUserById(@PathVariable("userId") Long userId);

}
