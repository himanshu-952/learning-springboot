package com.himanshu.journalApp.controllers;


import com.himanshu.journalApp.entity.Users;
import com.himanshu.journalApp.service.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepoService userService;


    @PutMapping("/update")
    public ResponseEntity<Users> updateUSer(@RequestBody Users newuser) {
        Users user = userService.saveEntryAndReturn(newuser);
        if (user != null) {

            user.setPassword(newuser.getPassword());
            userService.saveEntry(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
