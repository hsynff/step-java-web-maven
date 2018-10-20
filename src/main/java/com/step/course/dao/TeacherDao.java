package com.step.course.dao;

import com.step.course.model.Teacher;

import java.util.List;

public interface TeacherDao {

    List<Teacher> getAllTeacher();
    boolean deleteTeacher(int idTeacher);
    List<Teacher> getTeachersByCourseId(int idCourse);
    boolean addTeacher(Teacher teacher);
    boolean editTeacher(Teacher teacher);
    Teacher getTeacherById(int id);

}
