package com.springboot.journal_app.controller;

import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Get all Users
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Add new user
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody User newUser){ //localhost:8080/users
        try{
            userService.saveUser(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
        }
    }

    //find user by ID
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getUserById(@PathVariable long myId){ //localhost:8080/journal
        Optional<User> jE = userService.findById(myId);
        if(jE.isPresent()){
            return new ResponseEntity<>(jE.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Update User
    @PutMapping("/{userName}")
    public ResponseEntity<?>  updateUser(@RequestBody User user, @PathVariable String userName){ //localhost:8080/journal
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
