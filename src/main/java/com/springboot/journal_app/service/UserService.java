package com.springboot.journal_app.service;


import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional
    public void saveUser(User user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
    }

    @Transactional
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
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

    @Transactional
    public void deleteByUserName(String userName){
        userRepo.deleteByUserName(userName);
    }

}
