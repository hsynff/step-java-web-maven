package com.step.course.service;

import com.step.course.dao.StudentDao;
import com.step.course.model.Student;
import com.step.course.model.StudentSearch;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    @Override
    public List<Student> getAllStudent() {
        return studentDao.getAllStudent();
    }

    @Override
    public boolean deleteStudent(int idStudent) {
        return studentDao.deleteStudent(idStudent);
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(int idStudent) {
        return studentDao.getStudentById(idStudent);
    }

    @Override
    public boolean editStudent(Student student) {
        return studentDao.editStudent(student);
    }

    @Override
    public List<Student> advSearchStudent(StudentSearch studentSearch) {
        return studentDao.advSearchStudent(studentSearch);
    }
}
