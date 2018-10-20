package com.step.course.servlet;

import com.step.course.constants.NavigationConstants;
import com.step.course.dao.CourseDaoImpl;
import com.step.course.model.Course;
import com.step.course.service.CourseService;
import com.step.course.service.CourseServiceImpl;
import com.step.course.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseServlet", urlPatterns = "/cs")
public class CourseServlet extends HttpServlet {
    private CourseService courseService = new CourseServiceImpl(new CourseDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequsest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequsest(request, response);
    }

    protected void processRequsest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = null;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        if (action.equals(NavigationConstants.ACTION_GET_ALL_COURSE)) {
            List<Course> list = courseService.getAllCourse();
            request.setAttribute("courseList", list);

            request.getRequestDispatcher(NavigationConstants.PAGE_COURSE_TABLE).forward(request, response);


        }else if (action.equals(NavigationConstants.ACTION_DELETE_COURSE)){
            int id = Integer.parseInt(request.getParameter("id"));

            boolean result = courseService.deleteCourse(id);

            if (!result){
                throw new ServletException();
            }

        }else if (action.equals(NavigationConstants.ACTION_ADD_COURSE)){
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            String duration = request.getParameter("duration");


            Course course = new Course();
            course.setName(name);
            course.setDesc(desc);
            course.setDuration(Integer.parseInt(duration));

            boolean result = courseService.addCourse(course);
            if (!result){
                throw new ServletException();
            }
        }else if (action.equals(NavigationConstants.ACTION_EDIT_COURSE)){

            String name = request.getParameter("name");
            String duration = request.getParameter("duration");
            String desc = request.getParameter("desc");
            String id = request.getParameter("id");

            if (!ValidationUtil.validate(name, duration, desc, id)) {
                throw new ServletException();
            }

            Course course = new Course();
            course.setId(Integer.parseInt(id));
            course.setDuration(Integer.parseInt(duration));
            course.setDesc(desc);
            course.setName(name);
            if (!courseService.editCourse(course)){
                throw new ServletException();
            }
        }

    }
}
