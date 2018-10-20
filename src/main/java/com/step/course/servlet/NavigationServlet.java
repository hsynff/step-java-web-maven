package com.step.course.servlet;

import com.step.course.constants.NavigationConstants;
import com.step.course.dao.CourseDaoImpl;
import com.step.course.dao.StudentDaoImpl;
import com.step.course.dao.TeacherDaoImpl;
import com.step.course.dao.UserDaoImpl;
import com.step.course.model.Course;
import com.step.course.model.Role;
import com.step.course.model.Student;
import com.step.course.model.Teacher;
import com.step.course.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NavigationServlet", urlPatterns = "/ns")
public class NavigationServlet extends HttpServlet {

    private CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    private StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    private TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());
    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = null;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        if(action.equals(NavigationConstants.ACTION_GET_NEW_STUDENT_JSP)){

            List<Course> list = courseService.getAllCourse();

            request.setAttribute("courseList", list);
            request.getRequestDispatcher(NavigationConstants.PAGE_NEW_STUDENT_DIALOG).forward(request,response);

        }else if(action.equals(NavigationConstants.ACTION_GET_NEW_TEACHER_JSP)){

            List<Course> list = courseService.getAllCourse();
            request.setAttribute("courseList", list);
            request.getRequestDispatcher(NavigationConstants.PAGE_NEW_TEACHER_DIALOG).forward(request, response);

        }else if(action.equals(NavigationConstants.ACTION_GET_NEW_COURSE_JSP)){

            request.getRequestDispatcher(NavigationConstants.PAGE_NEW_COURSE_DIALOG).forward(request, response);

        }else if(action.equals(NavigationConstants.ACTION_GET_EDIT_STUDENT_JSP)){
            int idStudent = Integer.parseInt(request.getParameter("id"));

            Student student = studentService.getStudentById(idStudent);

            List<Course> list = courseService.getAllCourse();

            if (student==null){
                throw new ServletException();
            }

            List<Teacher> teacherList = teacherService.getTeachersByCourseId(student.getTeacher().getCourse().getId());

            request.setAttribute("student", student);
            request.setAttribute("teacherList", teacherList);
            request.setAttribute("courseList", list);
            request.getRequestDispatcher(NavigationConstants.PAGE_EDIT_STUDENT_DIALOG).forward(request, response);

        }else if (action.equals(NavigationConstants.ACTION_GET_EDIT_TEACHER_JSP)){

            int idTeacher = Integer.parseInt(request.getParameter("id"));
            Teacher teacher = teacherService.getTeacherById(idTeacher);

            if (teacher == null){
                throw new ServletException();
            }

            List<Course> list = courseService.getAllCourse();
            request.setAttribute("courseList", list);
            request.setAttribute("teacher", teacher);
            request.getRequestDispatcher(NavigationConstants.PAGE_EDIT_TEACHER_DIALOG).forward(request, response);
        }else if(action.equals(NavigationConstants.ACTION_GET_EDIT_COURSE_JSP)){

            int id = Integer.parseInt(request.getParameter("id"));
            Course course = courseService.getCourseById(id);
            request.setAttribute("course", course);

            request.getRequestDispatcher(NavigationConstants.PAGE_EDIT_COURSE_DIALOG).forward(request, response);
        }else if (action.equals(NavigationConstants.ACTION_LOGIN)){
            request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN).forward(request, response);
        }else if(action.equals(NavigationConstants.ACTION_REGISTER)){

            List<Role> roles = userService.getAllRoles();

            request.setAttribute("roles", roles);
            request.getRequestDispatcher(NavigationConstants.PAGE_REGISTER).forward(request, response);
        }

    }
}


// Authentication/Authorization
// Filter classes
// Email send
// Crypto functions

