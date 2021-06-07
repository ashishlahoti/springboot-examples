package com.example.api.client;

import com.example.api.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UserMockClient {

    private Random random = new Random();
    private List<User> userList = new ArrayList<>(
            List.of(User.builder().id(1L).name("Adam").dateOfBirth(LocalDate.of(1950, 1, 1)).build(),
                    User.builder().id(2L).name("Bob").dateOfBirth(LocalDate.of(1990, 10, 30)).build(),
                    User.builder().id(3L).name("Charlie").dateOfBirth(LocalDate.of(1979, 7, 26)).build()));

    public List<User> getAllUsers() {
        userList.forEach(user -> user.setLastLogin(LocalDateTime.now().minusDays(random.nextInt(10))));
        return userList;
    }

    public User getUserById(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).peek(user -> {
            user.setLastLogin(LocalDateTime.now());
            user.setZonedDateTime(ZonedDateTime.now());
        }).findAny().orElse(null);
    }

    public Long createUser(User user) {
        Long id = (long) (this.userList.size() + 1);
        user.setId(id);
        userList.add(user);
        return id;
    }

    public boolean updateUser(Long id, User user) {
        userList.forEach(item -> {
            if (item.getId().equals(id)) {
                item.setName(user.getName());
                item.setDateOfBirth(user.getDateOfBirth());
            }
        });
        return true;
    }

    public boolean deleteUserById(Long id) {
        userList.removeIf(user -> user.getId().equals(id));
        return true;
    }

}
