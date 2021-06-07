package com.example.api.controller;

import com.example.api.domain.PostTestData;
import com.example.api.domain.UserTestData;
import com.example.api.service.PostService;
import com.example.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUsers_whenValidRequest_returnsValidResponse() throws Exception {
        when(userService.getAllUsers()).thenReturn(UserTestData.users());

        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(UserTestData.users().size())))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Adam")))
                .andExpect(jsonPath("$[0].dateOfBirth", equalTo("22 Aug 1986")));
    }

    @Test
    public void getAllUsers_whenServiceThrowException_returnsInternalServerError() throws Exception {
        when(userService.getAllUsers()).thenThrow(new RuntimeException("Oops, Something went wrong!"));

        mockMvc.perform(get("/users"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", equalTo("Internal Server Error")))
                .andExpect(jsonPath("$.debugMessage", equalTo("Oops, Something went wrong!")));
    }

    @Test
    public void getUserById_whenValidUserId_returnThatUser() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(UserTestData.user());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Adam")))
                .andExpect(jsonPath("$.dateOfBirth", equalTo("22 Aug 1986")));
    }

    @Test
    public void createUser_whenValidUserData_createAndReturnTheUserId() throws Exception {
        when(userService.createUser(UserTestData.user())).thenReturn(UserTestData.user().getId());

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(UserTestData.user())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updateUser_whenValidUserId_updateThatUser() throws Exception {

        mockMvc.perform(put("/users/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteUser_whenValidUserId_deleteThatUser() throws Exception {

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void patchUser_whenUnsupportedHttpVerb_returnsMethodNotAllowed() throws Exception {
        mockMvc.perform(patch("/users"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.message", equalTo("Method Not Allowed")))
                .andExpect(jsonPath("$.debugMessage", equalTo("Request method 'PATCH' not supported")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
