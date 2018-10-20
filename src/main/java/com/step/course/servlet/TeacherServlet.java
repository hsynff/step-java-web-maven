package com.step.course.servlet;

import com.step.course.constants.NavigationConstants;
import com.step.course.dao.TeacherDaoImpl;
import com.step.course.model.Course;
import com.step.course.model.Teacher;
import com.step.course.service.TeacherService;
import com.step.course.service.TeacherServiceImpl;
import com.step.course.util.ValidationUtil;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeacherServlet", urlPatterns = "/ts")
public class TeacherServlet extends HttpServlet {
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

        if (action.equals(NavigationConstants.ACTION_GET_ALL_TEACHER)) {
            List<Teacher> list = teacherService.getAllTeacher();
            request.setAttribute("teacherList", list);

            request.getRequestDispatcher(NavigationConstants.PAGE_TEACHER_TABLE).forward(request, response);

        }else if (action.equals(NavigationConstants.ACTION_DELETE_TEACHER)){
            int id = Integer.parseInt(request.getParameter("id"));

            boolean result = teacherService.deleteTeacher(id);

            if (!result){
                throw new ServletException();
            }
        }else if(action.equals(NavigationConstants.ACTION_GET_TEACHER_BY_COURSE_ID)){

            int id = Integer.parseInt(request.getParameter("id"));

            List<Teacher> list = teacherService.getTeachersByCourseId(id);

            JSONArray jsonArray = new JSONArray(list);
            System.out.println(jsonArray);

            response.setContentType("application/json");
            response.getWriter().write(jsonArray.toString());



//            request.setAttribute("teacherList", list);
//            request.getRequestDispatcher("/WEB-INF/fragments/teacher-options.jsp").forward(request, response);

        }else if (action.equals(NavigationConstants.ACTION_ADD_TEACHER)){

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String course = request.getParameter("course");

            boolean vResult = ValidationUtil.validate(firstName, lastName, course);

            if (!vResult){
                throw new ServletException();
            }

            Teacher teacher = new Teacher();
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            Course c = new Course();
            c.setId(Integer.parseInt(course));
            teacher.setCourse(c);

            boolean tResult = teacherService.addTeacher(teacher);

            if (!tResult){
                throw new ServletException();
            }

        }else if (action.equals(NavigationConstants.ACTION_EDIT_TEACHER)){


            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String idCourse = request.getParameter("idCourse");
            String teacherId = request.getParameter("teacherId");


            if (!ValidationUtil.validate(firstName, lastName, idCourse, teacherId)){
                throw new ServletException();
            }

            Teacher teacher = new Teacher();
            teacher.setId(Integer.parseInt(teacherId));
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            Course course = new Course();
            course.setId(Integer.parseInt(idCourse));
            teacher.setCourse(course);

            boolean result = teacherService.editTeacher(teacher);
            if (!result){
                throw new ServletException();
            }

        }

    }


}
