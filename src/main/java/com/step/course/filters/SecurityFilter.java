package com.step.course.filters;

import com.step.course.constants.UserConstants;
import com.step.course.dao.UserDaoImpl;
import com.step.course.model.Action;
import com.step.course.model.User;
import com.step.course.service.UserService;
import com.step.course.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {

    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    private List<Action> adminActions;
    private List<Action> managerActions;
    private List<Action> nonAuthActions;


    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getRequestURI().contains("resources")) {
            chain.doFilter(req, resp);
            return;
        }

        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");
        boolean isAllowed = false;

        if (action != null) {

            List<Action> list = new ArrayList<>();

            if (user==null){
                list = nonAuthActions;
            }else if (user.getRole().getId() == UserConstants.USER_ROLE_ADMIN){
                list = adminActions;
            }else if (user.getRole().getId() == UserConstants.USER_ROLE_MANAGER){
                list = managerActions;
            }

            isAllowed = list.stream().anyMatch(a -> a.getActionType().equalsIgnoreCase(action));


        } else if (request.getRequestURI().equals(request.getContextPath() + "/")) {
            isAllowed = (user != null);
        }


        if (isAllowed) {
            chain.doFilter(req, resp);
        } else {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Location", "/ns?action=login");


            if (!"XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
                response.sendRedirect("/ns?action=login");
            }


        }

    }

    public void init(FilterConfig config) throws ServletException {
        adminActions = userService.getActionsByRoleId(UserConstants.USER_ROLE_ADMIN);
        managerActions = userService.getActionsByRoleId(UserConstants.USER_ROLE_MANAGER);
        nonAuthActions = userService.getActionsByRoleId(UserConstants.USER_ROLE_UNAUTH);
    }

}
