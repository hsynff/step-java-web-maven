package com.step.course.dao;

import com.step.course.model.Course;
import com.step.course.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private final String GET_ALL_COURSE_SQL = "select * from course";
    private final String DELETE_COURSE_SQL = "delete from course where id_course=?";
    private final String ADD_COURSE_SQL = "insert into course(`name`, `desc`, duration) values(?,?,?)";
    private final String GET_COURSE_BY_ID_SQL = "select * from course where id_course = ?";
    private final String EDIT_COURSE_SQL = "update course set `name` = ?, `desc` = ?, duration = ? where id_course = ?";


    @Override
    public List<Course> getAllCourse() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        try{
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_COURSE_SQL);
            rs = ps.executeQuery();

            while (rs.next()){
                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                list.add(course);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con,rs,ps);
        }

        return list;
    }

    @Override
    public boolean deleteCourse(int idCourse) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(DELETE_COURSE_SQL);
            ps.setInt(1,idCourse);
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps);
        }

        return result;
    }

    @Override
    public boolean addCourse(Course course) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {

            con = DbUtil.getConnection();
            ps = con.prepareStatement(ADD_COURSE_SQL);
            ps.setString(1, course.getName());
            ps.setString(2, course.getDesc());
            ps.setInt(3, course.getDuration());
            ps.executeUpdate();
            result = true;

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con,ps);
        }

        return result;
    }

    @Override
    public Course getCourseById(int courseId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        Course course = null;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_COURSE_BY_ID_SQL);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            if (rs.next()){
                course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return course;
    }

    @Override
    public boolean editCourse(Course course) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(EDIT_COURSE_SQL);
            ps.setString(1, course.getName());
            ps.setString(2, course.getDesc());
            ps.setInt(3, course.getDuration());
            ps.setInt(4, course.getId());
            ps.executeUpdate();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps,rs);
        }
        return result;
    }

}
