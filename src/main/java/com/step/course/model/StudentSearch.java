package com.step.course.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class StudentSearch {
    private String studentName;
    private String studentSurname;
    private LocalDate dobMin;
    private LocalDate dobMax;
    private Integer idCourse;
    private Integer courseDurationMin;
    private Integer courseDurationMax;
    private Integer idTeacher;
    private String keyword;

    public StudentSearch() {

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            this.studentName = null;
        } else {
            this.studentName = studentName;
        }

    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        if (studentSurname == null || studentSurname.trim().isEmpty()) {
            this.studentSurname = null;
        } else {
            this.studentSurname = studentSurname;
        }

    }

    public LocalDate getDobMin() {
        return dobMin;
    }

    public void setDobMin(String dobMin) {
        if (dobMin == null || dobMin.trim().isEmpty()) {
            this.dobMin = null;
        } else {
            this.dobMin = LocalDate.parse(dobMin);
        }

    }

    public LocalDate getDobMax() {
        return dobMax;
    }

    public void setDobMax(String dobMax) {
        if (dobMax == null || dobMax.trim().isEmpty()) {
            this.dobMax = null;
        } else {
            this.dobMax = LocalDate.parse(dobMax);
        }
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        if (idCourse == null || idCourse.trim().isEmpty()) {
            this.idCourse = null;
        } else {
            this.idCourse = Integer.valueOf(idCourse);
        }
    }

    public Integer getCourseDurationMin() {
        return courseDurationMin;
    }

    public void setCourseDurationMin(String courseDurationMin) {
        if (courseDurationMin == null || courseDurationMin.trim().isEmpty()) {
            this.courseDurationMin = null;
        } else {
            this.courseDurationMin = Integer.valueOf(courseDurationMin);
        }
    }

    public Integer getCourseDurationMax() {
        return courseDurationMax;
    }

    public void setCourseDurationMax(String courseDurationMax) {
        if (courseDurationMax == null || courseDurationMax.trim().isEmpty()) {
            this.courseDurationMax = null;
        } else {
            this.courseDurationMax = Integer.valueOf(courseDurationMax);
        }
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        if (idTeacher == null || idTeacher.trim().isEmpty()) {
            this.idTeacher = null;
        } else {
            this.idTeacher = Integer.valueOf(idTeacher);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            this.keyword = null;
        } else {
            this.keyword = keyword;
        }
    }

    public boolean isAllFieldsNull(){
        List<Object> allFields = Arrays.asList(studentName,studentSurname,dobMin, dobMax, idCourse, courseDurationMin, courseDurationMax, keyword, idTeacher);
        for (Object o : allFields){
            if (o!=null){
                return false;
            }
        }

        return true;
    }
}
