package com.springboot.journal_app.repo;

import com.springboot.journal_app.respository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTests {

    @Autowired
    private UserRepoImpl userRepoimpl;

    @Test
    public void testSave(){
        Assertions.assertNotNull(userRepoimpl.getUserForSA());
    }
}
