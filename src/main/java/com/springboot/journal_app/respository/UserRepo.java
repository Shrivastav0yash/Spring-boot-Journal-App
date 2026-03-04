package com.springboot.journal_app.respository;

import com.springboot.journal_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName (String userName);
}
