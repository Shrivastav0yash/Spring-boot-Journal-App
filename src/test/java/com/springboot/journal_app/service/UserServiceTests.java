package com.springboot.journal_app.service;


import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvSources;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;


    //@Test
    @ParameterizedTest
    @ValueSource(strings = {
            "Ram",
            "vipul",
            "yash"
    })
    public void testFindByUserName(String name){
        //User testUser = userRepo.findByUserName("vipul");
        //assertTrue(!testUser.getUserName().isEmpty());
        assertNotNull(userRepo.findByUserName(name));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,10,12",
            "4,7,1"
    })
    public void test(int a ,int b, int expected){
        assertEquals(expected, a+b);
    }

}
