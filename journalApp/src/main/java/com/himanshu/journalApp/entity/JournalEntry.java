package com.himanshu.journalApp.entity;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Getter
@Setter
public class JournalEntry {


  @Id
    private ObjectId id;
    private LocalDateTime time;
    @Nonnull
    private String Title;
    @Nonnull
    private String Content;
    @Nonnull
  private String sentimentAnalysis;

}
