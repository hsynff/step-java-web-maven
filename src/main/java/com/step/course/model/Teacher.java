package com.step.course.model;

import lombok.Data;

@Data
public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private Course course;
}
