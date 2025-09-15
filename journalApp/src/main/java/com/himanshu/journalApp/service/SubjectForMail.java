package com.himanshu.journalApp.service;

public class SubjectForMail {
    public  static  String getSubject(String User , String sentiment){
        switch (sentiment){
            case "Neutral"
                : return "A balanced week for you";
            case "Happy"
                :return "A week full of positivity";
            case"Very Happy"
                : return "Overflowing Positivity";
            case"Sad"
                :return "A Gentle Reminder : You are not Alone";
            case"Very Sad"
                : return  "Reaching out with care";
            default:return "Cant do analysis";

        }
    }
}
