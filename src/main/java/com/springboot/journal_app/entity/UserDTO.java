package com.springboot.journal_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class UserDTO {

    private long userId;
    private String userName;
    private List<String> roles;
}
