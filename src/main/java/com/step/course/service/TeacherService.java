package com.step.course.service;

import com.step.course.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeacher();
    boolean deleteTeacher(int idTeacher);
    List<Teacher> getTeachersByCourseId(int idCourse);
    boolean addTeacher (Teacher teacher);
    boolean editTeacher(Teacher teacher);
    Teacher getTeacherById(int id);

}
