package com.himanshu.journalApp.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedularExample {
@Scheduled(cron = "*/5****")
    public void print(){
    System.out.println("this is to show scheduling");
}
}
