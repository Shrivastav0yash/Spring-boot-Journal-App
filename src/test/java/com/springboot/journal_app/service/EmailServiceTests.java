package com.springboot.journal_app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("yashshrivastav8979@gmail.com", "Testing java mail sender", "Hi, sab badiyaa chal rha hai");
    }

}
