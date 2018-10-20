package com.step.course.service;

import com.step.course.exceptions.*;
import com.step.course.model.Action;
import com.step.course.model.Role;
import com.step.course.model.User;

import java.util.List;

public interface UserService {
    List<Role> getAllRoles();
    boolean registerNewUser(User user) throws DuplicateEmailException;
    boolean activateUserByToken(String token, String newToken) throws InvalidTokenException;
    User loginUser(String email, String password)throws InvalidEmailException, InvalidPasswordException, InactiveAccountException;
    boolean isActionAllowed(int idRole, String action);
    List<Action> getActionsByRoleId(int idRole);
}
