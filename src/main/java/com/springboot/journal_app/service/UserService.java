package com.springboot.journal_app.service;


import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveEntry(User user){
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(long myId){
        return userRepo.findById(myId);
    }

    public void deleteById(long Id){
        userRepo.deleteById(Id);
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

}
