package com.himanshu.journalApp.service;


import com.himanshu.journalApp.entity.Users;

import com.himanshu.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> findByemail(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").and("email").ne("").and("sentimentAnalysis").is(true));
        return mongoTemplate.find(query , Users.class);
    }

    @Autowired
    private UserRepo userRepo;



    public boolean saveEntry(Users user) {
        user.setRole("USER");
     Users newuser=   userRepo.save(user);
        if(newuser!=null){
            return  true;
        }else{
            return  false;
        }
    }

    public void saveEntry(Users user , String role) {
        user.setRole(role);
        userRepo.save(user);
    }

    public List<Users> getall() {
        return userRepo.findAll();
    }

    public Optional<Users> getById(ObjectId myid) {
        return userRepo.findById(myid);
    }

    public Users getUserByName (String name){
         return  userRepo.findByusername(name);
    }


    public Users saveEntryAndReturn(Users entry) {
        userRepo.save(entry);
        return userRepo.findByusername(entry.getUsername());
    }
}
