package com.springboot.journal_app.controller;


import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    //Add new user
    @PostMapping("/create-user")
    public ResponseEntity<?> addNewUser(@RequestBody User newUser){ //localhost:8080/users
        try{
            userService.saveUser(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }
}
