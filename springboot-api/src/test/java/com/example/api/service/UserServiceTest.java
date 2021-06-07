package com.example.api.service;

import com.example.api.client.UserMockClient;
import com.example.api.domain.User;
import com.example.api.domain.UserTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserMockClient userMockClient;

    @Autowired
    private UserService userService;

    @Test
    public void getAllUsers_whenValidProviderResponse_returnAllUsers() {
        when(userMockClient.getAllUsers()).thenReturn(UserTestData.users());

        List<User> users = userService.getAllUsers();

        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getId()).isEqualTo(1);
        assertThat(users.get(0).getName()).isEqualTo("Adam");
        assertThat(users.get(0).getDateOfBirth().toString()).isEqualTo("1986-08-22");
    }

    @Test
    public void getUserById_whenValidUserId_returnThatUser() {
        when(userMockClient.getUserById(anyLong())).thenReturn(UserTestData.user());

        User user = userService.getUserById(1L);

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getName()).isEqualTo("Adam");
        assertThat(user.getDateOfBirth().toString()).isEqualTo("1986-08-22");
    }

    @Test
    public void createUser_whenValidUserData_createAndReturnUserId() {
        when(userMockClient.createUser(any(User.class))).thenReturn(UserTestData.user().getId());

        Long id = userService.createUser(UserTestData.user());

        assertThat(id).isEqualTo(1L);
    }

    @Test
    public void updateUser_whenValidUserData_updateAndReturnStatus() {
        when(userMockClient.updateUser(anyLong(), any(User.class))).thenReturn(true);

        Boolean status = userService.updateUser(UserTestData.user().getId(), UserTestData.user());

        assertThat(status).isTrue();
    }

    @Test
    public void deleteUser_whenValidUserId_deleteAndReturnStatus() {
        when(userMockClient.deleteUserById(anyLong())).thenReturn(true);

        Boolean status = userService.deleteUserById(UserTestData.user().getId());

        assertThat(status).isTrue();
    }
}
