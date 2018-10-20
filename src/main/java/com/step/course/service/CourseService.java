package com.step.course.service;

import com.step.course.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourse();
    boolean deleteCourse(int idCourse);
    boolean addCourse(Course course);
    Course getCourseById(int courseId);
    boolean editCourse(Course course);
}
