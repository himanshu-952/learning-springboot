package com.himanshu.journalApp.service;

import com.himanshu.journalApp.entity.JournalEntry;
import com.himanshu.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SentimentService {
    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private JournalEntryService journalEntryService;

    HashMap<String, Integer> sentimentMap;

    public String getSentiment(Users user) {
        try {
            sentimentMap = new HashMap<>();
            Users currUser = userRepoService.getUserByName(user.getUsername());
            List<JournalEntry> list = currUser.getListByUser().stream().filter(x -> x.getTime().isAfter(LocalDateTime.now().minusWeeks(1))).toList();
            for (JournalEntry entry : list) {
                sentimentMap.put(entry.getSentimentAnalysis(), sentimentMap.getOrDefault(entry.getSentimentAnalysis(), 0) + 1);
            }
            String dominantSentiment = null;
            int max = Integer.MIN_VALUE;
            for (Map.Entry<String, Integer> entry : sentimentMap.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                    dominantSentiment = entry.getKey();
                }
            }

            if (dominantSentiment == null) {
                return "No Sentiment Data";
            }


            switch (dominantSentiment) {
                case "Neutral":
                    return "Neutral";
                case "Happy":
                    return "Happy";
                case "Very Happy":
                    return "Very Happy";
                case "Sad":
                    return "Sad";
                case "Very Sad":
                    return "Very Sad";
                default:
                    return dominantSentiment;
            }

            }

        catch (Exception e){

           return "Error" +e.toString();
        }
    }
}
