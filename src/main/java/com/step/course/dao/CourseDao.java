package com.step.course.dao;

import com.step.course.model.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourse();
    boolean deleteCourse(int idCourse);
    boolean addCourse(Course course);
    Course getCourseById(int courseId);
    boolean editCourse(Course course);
}
