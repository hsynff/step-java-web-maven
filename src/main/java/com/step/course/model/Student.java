package com.step.course.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Teacher teacher;

}
