package com.step.course.dao;

import com.step.course.constants.MessageConstants;
import com.step.course.constants.UserConstants;
import com.step.course.exceptions.*;
import com.step.course.model.Action;
import com.step.course.model.Role;
import com.step.course.model.User;
import com.step.course.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final String GET_ALL_ROLE_SQL = "select * from role";
    private final String ADD_NEW_USER_SQL = "insert into user(email, password, token, status, id_role) values(?,?,?,?,?)";
    private final String GET_EMAIL_COUNT_SQL = "select count(*) as count from user where email=?";
    private final String GET_TOKEN_COUNT_SQL = "select count(*) as count from user where token=?";
    private final String ACTIVATE_USER_BY_TOKEN = "update user set status=? , token=? where token=?";
    private final String GET_USER_BY_EMAIL = "select * from user u inner join role r on u.id_role = r.id_role where u.email = ?";
    private final String GET_ROLE_ACTION_BY_ROLE_AND_ACTION = "select * from role_action ra inner join action a on ra.id_action = a.id_action where ra.id_role=? and a.action_type=?";
    private final String GET_ACTIONS_BY_ROLE_ID = "select * from role_action ra inner join action a on ra.id_action=a.id_action where id_role=?";

    @Override
    public List<Role> getAllRoles() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Role> list = new ArrayList<>();

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_ROLE_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id_role"));
                role.setRoleType(rs.getString("role_type"));

                list.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return list;
    }

    @Override
    public boolean registerNewUser(User user) throws DuplicateEmailException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            if (!isEmailValid(user.getEmail())) {
                throw new DuplicateEmailException(MessageConstants.ERROR_MESSAGE_DUPLICATE_EMAIL);
            }

            con = DbUtil.getConnection();
            ps = con.prepareStatement(ADD_NEW_USER_SQL);
            //email, password, token, status, id_role
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getToken());
            ps.setInt(4, user.getStatus());
            ps.setInt(5, user.getRole().getId());
            ps.executeUpdate();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps);
        }

        return result;
    }

    @Override
    public boolean activateUserByToken(String token, String newToken) throws InvalidTokenException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {

            if (!isTokenValid(token)) {
                throw new InvalidTokenException(MessageConstants.ERROR_MESSAGE_INVALID_TOKEN);
            }

            con = DbUtil.getConnection();
            ps = con.prepareStatement(ACTIVATE_USER_BY_TOKEN);
            ps.setInt(1, UserConstants.USER_STATUS_ACTIVE);
            ps.setString(2, newToken);
            ps.setString(3, token);

            ps.executeUpdate();
            result = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User loginUser(String email, String password) throws InvalidEmailException, InvalidPasswordException, InactiveAccountException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_USER_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (!rs.getString("password").equals(password)) {
                    throw new InvalidPasswordException(MessageConstants.ERROR_MESSAGE_INVALID_PASSWORD);
                }

                if (rs.getInt("status") == UserConstants.USER_STATUS_INACTIVE) {
                    throw new InactiveAccountException(MessageConstants.ERROR_MESSAGE_INACTIVE_ACCOUNT);
                }

                user = new User();
                user.setId(rs.getInt("id_user"));
                user.setToken(rs.getString("token"));
                user.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setId(rs.getInt("id_role"));
                r.setRoleType(rs.getString("role_type"));
                user.setRole(r);
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

            } else {
                throw new InvalidEmailException(MessageConstants.ERROR_MESSAGE_INVALID_EMAIL);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return user;
    }

    @Override
    public boolean isActionAllowed(int idRole, String action) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ROLE_ACTION_BY_ROLE_AND_ACTION);
            ps.setInt(1, idRole);
            ps.setString(2, action);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return result;
    }

    @Override
    public List<Action> getActionsByRoleId(int idRole) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Action> list = new ArrayList<>();

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ACTIONS_BY_ROLE_ID);
            ps.setInt(1, idRole);
            rs = ps.executeQuery();
            while (rs.next()) {
                Action action = new Action();
                action.setId(rs.getInt("id_action"));
                action.setActionType(rs.getString("action_type"));
                list.add(action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return list;
    }

    private boolean isTokenValid(String token) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_TOKEN_COUNT_SQL);
            ps.setString(1, token);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");

                if (count == 1) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return result;


    }


    private boolean isEmailValid(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_EMAIL_COUNT_SQL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("count") == 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return result;

    }
}
