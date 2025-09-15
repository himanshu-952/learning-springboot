package com.himanshu.journalApp.scheduler;

import com.himanshu.journalApp.entity.Users;

import com.himanshu.journalApp.service.*;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MailSchedule {
    @Autowired
   private MailService mailService;

    @Autowired
    private SentimentService sentimentService;

    @Autowired
    private UserRepoService userRepoService;





@Scheduled(cron = "0 0 9 * * MON")
    public void sendMail(){
    try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users currUser = userRepoService.getUserByName(authentication.getName());

        String sentiment = sentimentService.getSentiment(currUser);

        mailService.sendMail(currUser.getEmail() , SubjectForMail.getSubject(authentication.getName() , sentiment), BodyForMail.sentimentBody(authentication.getName() , sentiment));

    }

    catch (Exception e){
        System.out.println(e);
    }

}
}
