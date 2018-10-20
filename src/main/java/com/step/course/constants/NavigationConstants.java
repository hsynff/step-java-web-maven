package com.step.course.constants;

public class NavigationConstants {
    // Actions
    public final static String ACTION_GET_NEW_STUDENT_JSP = "getNewStudentJsp";
    public final static String ACTION_GET_NEW_TEACHER_JSP = "getNewTeacherJsp";
    public final static String ACTION_GET_NEW_COURSE_JSP = "getNewCourseJsp";
    public final static String ACTION_GET_EDIT_STUDENT_JSP = "getEditStudentJsp";
    public final static String ACTION_GET_EDIT_TEACHER_JSP = "getEditTeacherJsp";
    public final static String ACTION_GET_EDIT_COURSE_JSP = "getEditCourseJsp";
    public final static String ACTION_LOGIN = "login";
    public final static String ACTION_REGISTER = "register";
    public final static String ACTION_GET_ALL_COURSE = "getAllCourse";
    public final static String ACTION_DELETE_COURSE = "deleteCourse";
    public final static String ACTION_ADD_COURSE = "addCourse";
    public final static String ACTION_EDIT_COURSE = "editCourse";
    public final static String ACTION_GET_ALL_STUDENT = "getAllStudent";
    public final static String ACTION_DELETE_STUDENT = "deleteStudent";
    public final static String ACTION_ADD_STUDENT = "addStudent";
    public final static String ACTION_EDIT_STUDENT = "editStudent";
    public final static String ACTION_SEARCH = "search";
    public final static String ACTION_GET_ALL_TEACHER = "getAllTeacher";
    public final static String ACTION_DELETE_TEACHER = "deleteTeacher";
    public final static String ACTION_GET_TEACHER_BY_COURSE_ID = "getTeachersByCourseId";
    public final static String ACTION_ADD_TEACHER = "addTeacher";
    public final static String ACTION_EDIT_TEACHER = "editTeacher";
    public final static String ACTION_DO_REGISTER = "doRegister";
    public final static String ACTION_ACTIVATE = "activate";
    public final static String ACTION_DO_LOGIN = "doLogin";
    public final static String ACTION_LOGOUT = "logout";

    //JSP
    private final static String PAGE_PREFIX_VIEW = "/WEB-INF/view/";
    private final static String PAGE_PREFIX_FRAGMENTS = "/WEB-INF/fragments/";

    public final static String PAGE_INDEX = PAGE_PREFIX_VIEW+"index.jsp";
    public final static String PAGE_LOGIN = PAGE_PREFIX_VIEW+"login.jsp";
    public final static String PAGE_REGISTER = PAGE_PREFIX_VIEW+"register.jsp";
    public final static String PAGE_SEARCH = PAGE_PREFIX_VIEW+"search.jsp";
    public final static String PAGE_COURSE_TABLE = PAGE_PREFIX_FRAGMENTS+"course-table.jsp";
    public final static String PAGE_STUDENT_TABLE = PAGE_PREFIX_FRAGMENTS+"student-table.jsp";
    public final static String PAGE_TEACHER_TABLE = PAGE_PREFIX_FRAGMENTS+"teacher-table.jsp";
    public final static String PAGE_EDIT_COURSE_DIALOG = PAGE_PREFIX_FRAGMENTS+"edit-course-dialog.jsp";
    public final static String PAGE_EDIT_STUDENT_DIALOG = PAGE_PREFIX_FRAGMENTS+"edit-student-dialog.jsp";
    public final static String PAGE_EDIT_TEACHER_DIALOG = PAGE_PREFIX_FRAGMENTS+"edit-teacher-dialog.jsp";
    public final static String PAGE_NEW_COURSE_DIALOG = PAGE_PREFIX_FRAGMENTS+"new-course-dialog.jsp";
    public final static String PAGE_NEW_STUDENT_DIALOG = PAGE_PREFIX_FRAGMENTS+"new-student-dialog.jsp";
    public final static String PAGE_NEW_TEACHER_DIALOG = PAGE_PREFIX_FRAGMENTS+"new-teacher-dialog.jsp";

}
