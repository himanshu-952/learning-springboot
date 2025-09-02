package com.himanshu.journalApp.controllers;


import com.himanshu.journalApp.entity.Users;
import com.himanshu.journalApp.service.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserRepoService userRepoService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<Users> list= userRepoService.getall();
        if(list!=null){
            return  new ResponseEntity<>(list , HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/add-admin")
    public ResponseEntity<?> addAdmin(@RequestBody Users user){
         userRepoService.saveEntry(user , "ADMIN");
         return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
