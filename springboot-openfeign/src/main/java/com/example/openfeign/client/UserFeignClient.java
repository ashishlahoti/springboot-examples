package com.example.openfeign.client;

import com.example.openfeign.config.ApacheHttp5FeignSslClientConfig;
import com.example.openfeign.model.UserRequest;
import com.example.openfeign.model.ListUserResponse;
import com.example.openfeign.model.SingleUserResponse;
import com.example.openfeign.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userFeignClient",
    url = "${client.api.baseUrl}",
    configuration = ApacheHttp5FeignSslClientConfig.class,
    fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/api/users")
    ListUserResponse getUserList(@RequestParam("page") Integer page);

    @GetMapping("/api/users")
    ListUserResponse getUserListDelayed(@RequestParam("page") Integer page, @RequestParam("delay") Integer delay);

    @GetMapping("/api/users/{userId}")
    SingleUserResponse getUserById(@PathVariable("userId") Long userId);

    @PostMapping("/api/users")
    User createUser(@RequestBody UserRequest userRequest);

    @PutMapping("/api/users")
    User updateUser(@RequestBody UserRequest userRequest);

    @DeleteMapping("/api/users/{userId}")
    void deleteUserById(@PathVariable("userId") Long userId);
}
