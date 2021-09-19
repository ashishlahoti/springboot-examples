package com.example.openfeign.client;

import com.example.openfeign.model.SingleUserResponse;
import com.example.openfeign.model.User;
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

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "client.api.baseUrl=http://localhost:" + UserFeignClientTest.port
    })
@AutoConfigureWireMock(port = UserFeignClientTest.port)
public class UserFeignClientTest {

    @Autowired
    UserFeignClient userFeignClient;

    public static final int port = 9091;

    @Test
    public void getUserById_whenValidClient_returnValidResponse() throws Exception {
        // Using WireMock to mock client API:
        stubFor(get(urlEqualTo("/api/users/1"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody(read("stubs/user.json"))));

        SingleUserResponse userData = userFeignClient.getUserById(1L);
        User user = userData.getUser();

        // We're asserting if WireMock responded properly
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getFirstName()).isEqualTo("George");
        assertThat(user.getLastName()).isEqualTo("Bluth");
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
