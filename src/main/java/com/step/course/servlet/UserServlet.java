package com.step.course.servlet;

import com.step.course.constants.MessageConstants;
import com.step.course.constants.NavigationConstants;
import com.step.course.constants.UserConstants;
import com.step.course.dao.UserDaoImpl;
import com.step.course.exceptions.*;
import com.step.course.model.Role;
import com.step.course.model.User;
import com.step.course.service.UserService;
import com.step.course.service.UserServiceImpl;
import com.step.course.util.CryptoUtil;
import com.step.course.util.MailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(name = "UserServlet", urlPatterns = "/us")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = null;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        if (action.equals(NavigationConstants.ACTION_DO_REGISTER)) {

            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String passRetype = request.getParameter("passRetype");
            String idRole = request.getParameter("idRole");

            User user = new User();
            user.setEmail(email);
            user.setPassword(CryptoUtil.inputToHash(pass));
            Role role = new Role();
            role.setId(Integer.parseInt(idRole));
            user.setRole(role);

            user.setStatus(UserConstants.USER_STATUS_INACTIVE);

            String token = UUID.randomUUID().toString();
            user.setToken(token);

            List<Role> roles = userService.getAllRoles();
            request.setAttribute("roles", roles);
            try {

                boolean result = userService.registerNewUser(user);
                if (result) {

                    String body = "Please click the link below to activate your account:" +
                            "http://localhost:8080/us?action=activate&token=" + user.getToken();
                    ExecutorService service = null;
                    try {
                        service = Executors.newFixedThreadPool(20);
                        service.submit(() ->{
                            MailUtil.sendEmail(user.getEmail(), "Support", body);
                        });
                    }finally {
                        if (service!=null){
                            service.shutdown();
                        }
                    }


                    request.setAttribute("message", MessageConstants.SUCCESS_MESSAGE_REGISTER);
                } else {
                    request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL);
                }

                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN).forward(request, response);
            } catch (DuplicateEmailException e) {
                e.printStackTrace();
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(NavigationConstants.PAGE_REGISTER).forward(request, response);
            }


        } else if (action.equals(NavigationConstants.ACTION_ACTIVATE)) {
            String token = request.getParameter("token");
            String newToken = UUID.randomUUID().toString();

            try {
                boolean result = userService.activateUserByToken(token, newToken);
                if (result) {
                    request.setAttribute("message", MessageConstants.SUCCES_MESSAGE_ACTIVATE);
                } else {
                    request.setAttribute("message", MessageConstants.ERROR_MESSAGE_INTERNAL);
                }
                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN).forward(request, response);
            } catch (InvalidTokenException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(NavigationConstants.PAGE_REGISTER).forward(request, response);
            }
        } else if (action.equals(NavigationConstants.ACTION_DO_LOGIN)) {
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");

            try {
                User user = userService.loginUser(email, CryptoUtil.inputToHash(pass));
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    response.sendRedirect("/");
                }
            } catch (InvalidEmailException | InvalidPasswordException | InactiveAccountException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(NavigationConstants.PAGE_LOGIN).forward(request,response);
            }
        }else  if (action.equals(NavigationConstants.ACTION_LOGOUT)){
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null){
                session.removeAttribute("user");
                session.invalidate();
                response.sendRedirect("/ns?action=login");
            }
        }
    }
}

