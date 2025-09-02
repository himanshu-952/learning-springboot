package com.himanshu.journalApp;

import com.himanshu.journalApp.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void sendTest(){
        mailService.sendMail("test@example.com" , "testing mail" , "hello this is a test");


    }
}
