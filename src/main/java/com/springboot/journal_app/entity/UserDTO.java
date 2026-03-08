package com.springboot.journal_app.entity;

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
