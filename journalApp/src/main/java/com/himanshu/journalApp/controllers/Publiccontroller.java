package com.himanshu.journalApp.controllers;

import com.himanshu.journalApp.entity.Users;
import com.himanshu.journalApp.service.DetailService;
import com.himanshu.journalApp.service.UserRepoService;
import com.himanshu.journalApp.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@Slf4j

@RestController
@RequestMapping("/api/public")
public class Publiccontroller {

    @Autowired
   private UserRepoService userRepoService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
  private  AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
   private DetailService detailService;

    @GetMapping("/check")
    public String healthCheck(){
        return "ok hai";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> newUser(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepoService.saveEntry(user)){
            return  new ResponseEntity<>("User Created" , HttpStatus.CREATED);
        }else {
            return  new ResponseEntity<>("Failed" , HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody Users user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword()));
            UserDetails currUser=detailService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(user.getUsername());
            return  new ResponseEntity<>(jwt , HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error("Exception occurred" , e);
        return  new ResponseEntity<>("Invalid user" , HttpStatus.BAD_REQUEST);
        }

    }
}
