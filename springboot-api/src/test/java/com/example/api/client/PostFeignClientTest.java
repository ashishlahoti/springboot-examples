package com.example.api.client;

import com.example.api.domain.Post;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
public class PostFeignClientTest {

    @Autowired
    PostFeignClient postFeignClient;

    @Test
    public void getAllPosts_whenValidClient_returnValidResponse() throws Exception {
        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/posts"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/posts.json"))));

        List<Post> posts = postFeignClient.getAllPosts();
        Post post = posts.get(0);

        // We're asserting if WireMock responded properly
        assertThat(posts).hasSize(10);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getUserId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("title");
        assertThat(post.getBody()).isEqualTo("body");
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
