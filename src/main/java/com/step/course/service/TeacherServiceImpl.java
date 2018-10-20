package com.step.course.service;

import com.step.course.dao.TeacherDao;
import com.step.course.model.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService{
    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDao.getAllTeacher();
    }

    @Override
    public boolean deleteTeacher(int idTeacher) {
        return teacherDao.deleteTeacher(idTeacher);
    }

    @Override
    public List<Teacher> getTeachersByCourseId(int idCourse) {
        return teacherDao.getTeachersByCourseId(idCourse);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public boolean editTeacher(Teacher teacher) {
        return teacherDao.editTeacher(teacher);
    }

    @Override
    public Teacher getTeacherById(int id) {
        return teacherDao.getTeacherById(id);
    }

}
