package com.step.course.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String token;
    private int status;
    private Role role;

}
