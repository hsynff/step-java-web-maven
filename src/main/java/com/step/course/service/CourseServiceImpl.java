package com.step.course.service;

import com.step.course.dao.CourseDao;
import com.step.course.model.Course;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public boolean deleteCourse(int idCourse) {
        return courseDao.deleteCourse(idCourse);
    }

    @Override
    public boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public Course getCourseById(int courseId) {
        return courseDao.getCourseById(courseId);
    }

    @Override
    public boolean editCourse(Course course) {
        return courseDao.editCourse(course);
    }

}
