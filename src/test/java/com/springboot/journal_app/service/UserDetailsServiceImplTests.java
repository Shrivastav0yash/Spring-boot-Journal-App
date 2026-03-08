package com.springboot.journal_app.service;

import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collections;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
       when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("vipul").password("adadad").roles(Collections.singletonList("USER")).build());
        UserDetails user = userDetailsService.loadUserByUsername("vipul");
        Assertions.assertNotNull(user);
    }
}
