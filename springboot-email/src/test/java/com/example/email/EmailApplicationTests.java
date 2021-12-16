package com.example.email;

import com.example.email.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmailApplicationTests {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    void contextLoads() {
        assertThat(emailSenderService).isNotNull();
    }
}
