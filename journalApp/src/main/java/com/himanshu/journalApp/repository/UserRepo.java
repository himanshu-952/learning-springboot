package com.himanshu.journalApp.repository;

import com.himanshu.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByusername(String name);
}
