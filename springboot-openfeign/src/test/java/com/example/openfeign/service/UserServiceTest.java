package com.example.openfeign.service;

import com.example.openfeign.client.UserFeignClient;
import com.example.openfeign.domain.UserTestData;
import com.example.openfeign.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserFeignClient userFeignClient;

    @Autowired
    private UserService userService;

    @Test
    public void getUserById_whenValidUserId_returnThatUser() {
        when(userFeignClient.getUserById(anyLong())).thenReturn(UserTestData.userData());

        User user = userService.getUserById(1L);

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getFirstName()).isEqualTo("George");
        assertThat(user.getLastName()).isEqualTo("Bluth");
    }

}
