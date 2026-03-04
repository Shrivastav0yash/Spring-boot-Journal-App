package com.springboot.journal_app.service;


import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void saveUser(User user){
        userRepo.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAll(){
        return userRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long myId){
        return userRepo.findById(myId);
    }

    @Transactional
    public void deleteById(long Id){
        userRepo.deleteById(Id);
    }

    @Transactional(readOnly = true)
    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

}
