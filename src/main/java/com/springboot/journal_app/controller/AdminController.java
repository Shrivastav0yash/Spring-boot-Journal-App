package com.springboot.journal_app.controller;

import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.entity.UserDTO;
import com.springboot.journal_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null &&!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("save-admin")
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        try{
            userService.saveAdmin(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-user/{myId}")
    public ResponseEntity<?> getById(@PathVariable long myId){
        Optional<User> user = userService.findById(myId);
        if(user.isPresent()){
            User u = user.get();
            UserDTO disUser = new UserDTO();
            disUser.setUserId(u.getUserId());
            disUser.setUserName(u.getUserName());
            disUser.setRoles(u.getRoles());
            return new ResponseEntity<>(disUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
