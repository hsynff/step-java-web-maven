package com.step.course.service;

import com.step.course.dao.UserDao;
import com.step.course.exceptions.*;
import com.step.course.model.Action;
import com.step.course.model.Role;
import com.step.course.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }


    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    public boolean registerNewUser(User user) throws DuplicateEmailException {
        return userDao.registerNewUser(user);
    }

    @Override
    public boolean activateUserByToken(String token, String newToken) throws InvalidTokenException {
        return userDao.activateUserByToken(token, newToken);
    }

    @Override
    public User loginUser(String email, String password) throws InvalidEmailException, InvalidPasswordException, InactiveAccountException {
        return userDao.loginUser(email, password);
    }

    @Override
    public boolean isActionAllowed(int idRole, String action) {
        return userDao.isActionAllowed(idRole, action);
    }

    @Override
    public List<Action> getActionsByRoleId(int idRole) {
        return userDao.getActionsByRoleId(idRole);
    }
}
