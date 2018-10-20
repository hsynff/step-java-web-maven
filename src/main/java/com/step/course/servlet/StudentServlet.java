package com.step.course.servlet;

import com.step.course.constants.NavigationConstants;
import com.step.course.dao.CourseDaoImpl;
import com.step.course.dao.StudentDaoImpl;
import com.step.course.dao.TeacherDaoImpl;
import com.step.course.model.Course;
import com.step.course.model.Student;
import com.step.course.model.StudentSearch;
import com.step.course.model.Teacher;
import com.step.course.service.*;
import com.step.course.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/ss")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    private CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());
    private TeacherService teacherService = new TeacherServiceImpl(new TeacherDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequsest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequsest(request,response);
    }

    protected void processRequsest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = null;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        if (action.equals(NavigationConstants.ACTION_GET_ALL_STUDENT)) {
            List<Student> list = studentService.getAllStudent();
            request.setAttribute("studentList", list);

            request.getRequestDispatcher(NavigationConstants.PAGE_STUDENT_TABLE).forward(request, response);

        }else if (action.equals(NavigationConstants.ACTION_DELETE_STUDENT)){
            int id = Integer.parseInt(request.getParameter("id"));

            boolean result = studentService.deleteStudent(id);

            if (!result){
                throw new ServletException();
            }


        }else if (action.equals(NavigationConstants.ACTION_ADD_STUDENT)){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dob = request.getParameter("dob");
            String idTeacher = request.getParameter("idTeacher");

            if (!ValidationUtil.validate(firstName,lastName,dob,idTeacher)){
                throw new ServletException();
            }

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDateOfBirth(LocalDate.parse(dob));
            Teacher teacher = new Teacher();
            teacher.setId(Integer.parseInt(idTeacher));
            student.setTeacher(teacher);


            boolean result = studentService.addStudent(student);
            if(!result){
                throw new ServletException();
            }

        }else if (action.equals(NavigationConstants.ACTION_EDIT_STUDENT)){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dob = request.getParameter("dob");
            String idTeacher = request.getParameter("idTeacher");
            String idStudent = request.getParameter("idStudent");

            if (!ValidationUtil.validate(firstName,lastName,dob,idTeacher, idStudent)){
                throw new ServletException();
            }

            Student student = new Student();
            student.setId(Integer.parseInt(idStudent));
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDateOfBirth(LocalDate.parse(dob));
            Teacher teacher = new Teacher();
            teacher.setId(Integer.parseInt(idTeacher));
            student.setTeacher(teacher);


            boolean result = studentService.editStudent(student);
            if(!result){
                throw new ServletException();
            }
        }else if(action.equals(NavigationConstants.ACTION_SEARCH)){

            String studentName = request.getParameter("studentName");
            String studentSurname = request.getParameter("studentSurname");
            String dobMin = request.getParameter("dobMin");
            String dobMax = request.getParameter("dobMax");
            String idCourse = request.getParameter("idCourse");
            String courseDurationMin = request.getParameter("courseDurationMin");
            String courseDurationMax = request.getParameter("courseDurationMax");
            String idTeacher = request.getParameter("idTeacher");
            String keyword = request.getParameter("keyword");

            StudentSearch studentSearch = new StudentSearch();
            studentSearch.setStudentName(studentName);
            studentSearch.setStudentSurname(studentSurname);
            studentSearch.setKeyword(keyword);
            studentSearch.setCourseDurationMax(courseDurationMax);
            studentSearch.setCourseDurationMin(courseDurationMin);
            studentSearch.setDobMax(dobMax);
            studentSearch.setDobMin(dobMin);
            studentSearch.setIdCourse(idCourse);
            studentSearch.setIdTeacher(idTeacher);


            List<Student> list = studentService.advSearchStudent(studentSearch);

            List<Course> courseList = courseService.getAllCourse();
            List<Teacher> teacherList = teacherService.getAllTeacher();

            request.setAttribute("resultList", list);
            request.setAttribute("courseList", courseList);
            request.setAttribute("teacherList", teacherList);
            request.getRequestDispatcher(NavigationConstants.PAGE_SEARCH).forward(request, response);
        }





    }
}
