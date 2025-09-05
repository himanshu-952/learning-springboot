package com.himanshu.journalApp.entity;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users_entry")
@Data
@NoArgsConstructor

public class Users {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @Nonnull
    private String username;

    @Nonnull
    private String password;

    private String email;



    private String role;

    @DBRef
    private List<JournalEntry> listByUser = new ArrayList<>();
}
