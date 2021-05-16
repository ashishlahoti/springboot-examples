package com.example.api.controller;

import com.example.api.domain.PostTestData;
import com.example.api.service.PostService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void getAllPosts_whenValidRequest_returnsValidResponse() throws Exception {
        when(postService.getAllPosts()).thenReturn(PostTestData.posts());

        mockMvc.perform(get("/posts"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(PostTestData.posts().size())))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].userId", equalTo(1)))
                .andExpect(jsonPath("$[0].title", equalTo("title")))
                .andExpect(jsonPath("$[0].body", equalTo("body")));
    }

    @Test
    public void getAllPosts_whenServiceThrowException_returnsInternalServerError() throws Exception {
        when(postService.getAllPosts()).thenThrow(new RuntimeException("Oops, Something went wrong!"));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", equalTo("Internal Server Error")))
                .andExpect(jsonPath("$.debugMessage", equalTo("Oops, Something went wrong!")));
    }

    @Test
    public void getPostById_whenValidPostId_returnThatPost() throws Exception {
        when(postService.getPostById(anyLong())).thenReturn(PostTestData.post());

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.userId", equalTo(1)))
                .andExpect(jsonPath("$.title", equalTo("title")))
                .andExpect(jsonPath("$.body", equalTo("body")));
    }

    @Test
    public void createPost_whenValidPostData_createAndReturnThatPost() throws Exception {
        when(postService.createPost(PostTestData.post())).thenReturn(PostTestData.post());

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(PostTestData.post())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updatePost_whenValidPostId_updateThatPost() throws Exception {

        mockMvc.perform(put("/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(PostTestData.post())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deletePost_whenValidPostId_deleteThatPost() throws Exception {

        mockMvc.perform(delete("/posts/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void patchPost_whenUnsupportedHttpVerb_returnsMethodNotAllowed() throws Exception {
        mockMvc.perform(patch("/posts"))
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
