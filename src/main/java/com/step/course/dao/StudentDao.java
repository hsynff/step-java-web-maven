package com.step.course.dao;

import com.step.course.model.Student;
import com.step.course.model.StudentSearch;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudent();
    boolean deleteStudent(int idStudent);
    boolean addStudent(Student student);
    Student getStudentById(int idStudent);
    boolean editStudent(Student student);
    List<Student> advSearchStudent(StudentSearch studentSearch);
}
