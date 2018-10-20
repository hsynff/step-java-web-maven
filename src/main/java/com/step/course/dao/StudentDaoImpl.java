package com.step.course.dao;

import com.step.course.model.Course;
import com.step.course.model.Student;
import com.step.course.model.StudentSearch;
import com.step.course.model.Teacher;
import com.step.course.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final String GET_ALL_STUDENT_SQL = "select s.id_student, s.first_name as s_first_name, s.last_name as s_last_name, s.date_of_birth, c.id_course, c.name as course_name, c.desc, c.duration, t.id_teacher, t.first_name as t_first_name, t.last_name as t_last_name from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on t.id_course=c.id_course";
    private final String DELETE_STUDENT_SQL = "delete from student where id_student=?";
    private final String ADD_STUDENT_SQL = "insert into student(first_name,last_name,date_of_birth,id_teacher) values(?,?,?,?)";
    private final String GET_STUDENT_BY_ID_SQL = "select s.id_student, s.first_name as s_first_name, s.last_name as s_last_name, s.date_of_birth, c.id_course, c.name as course_name, c.desc, c.duration, t.id_teacher, t.first_name as t_first_name, t.last_name as t_last_name from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on t.id_course=c.id_course where s.id_student=?";
    private final String EDIT_STUDENT_SQL = "update student set first_name = ?, last_name = ?, date_of_birth = ?, id_teacher = ? where id_student = ?";
    private final String ADV_SEARCH_STUDENT_SQL = "select s.id_student, s.first_name as s_first_name, s.last_name as s_last_name, s.date_of_birth, c.id_course, c.name as course_name, c.desc, c.duration, t.id_teacher, t.first_name as t_first_name, t.last_name as t_last_name from student s inner join teacher t on s.id_teacher=t.id_teacher inner join course c on t.id_course=c.id_course";

    @Override
    public List<Student> getAllStudent() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_ALL_STUDENT_SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("s_first_name"));
                student.setLastName(rs.getString("s_last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());

                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("course_name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));

                teacher.setCourse(course);
                student.setTeacher(teacher);

                list.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return list;
    }

    @Override
    public boolean deleteStudent(int idStudent) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(DELETE_STUDENT_SQL);
            ps.setInt(1, idStudent);
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps);
        }

        return result;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {

            con = DbUtil.getConnection();
            ps = con.prepareStatement(ADD_STUDENT_SQL);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getDateOfBirth().toString());
            ps.setInt(4, student.getTeacher().getId());
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
    public Student getStudentById(int idStudent) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;

        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_STUDENT_BY_ID_SQL);
            ps.setInt(1, idStudent);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("s_first_name"));
                student.setLastName(rs.getString("s_last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());

                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("course_name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));

                teacher.setCourse(course);
                student.setTeacher(teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps, rs);
        }

        return student;
    }

    @Override
    public boolean editStudent(Student student) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(EDIT_STUDENT_SQL);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getDateOfBirth().toString());
            ps.setInt(4, student.getTeacher().getId());
            ps.setInt(5, student.getId());
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
    public List<Student> advSearchStudent(StudentSearch studentSearch) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        List<Object> parametersList = null;
        StringBuilder sql = new StringBuilder(ADV_SEARCH_STUDENT_SQL);
        try {

            if (!studentSearch.isAllFieldsNull()) {
                sql.append(" where");
                boolean appendAnd = false;
                parametersList = new ArrayList<>();

                if (studentSearch.getStudentName() != null) {
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.first_name=?");
                    parametersList.add(studentSearch.getStudentName());
                    appendAnd = true;

                }

                if (studentSearch.getStudentSurname() != null) {
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.last_name=?");
                    parametersList.add(studentSearch.getStudentSurname());
                    appendAnd = true;
                }

                if (studentSearch.getCourseDurationMin()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" c.duration >= ?");
                    parametersList.add(studentSearch.getCourseDurationMin());
                    appendAnd = true;
                }

                if (studentSearch.getCourseDurationMax()!=null){
                    if (appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" c.duration <= ?");
                    parametersList.add(studentSearch.getCourseDurationMax());
                    appendAnd = true;
                }
                if (studentSearch.getDobMax()!=null) {
                    if(appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.date_of_birth<=?");
                    parametersList.add(studentSearch.getDobMax());
                    appendAnd=true;
                }
                if (studentSearch.getDobMin()!=null) {
                    if(appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" s.date_of_birth>=?");
                    parametersList.add(studentSearch.getDobMin());
                    appendAnd=true;
                }
                if(studentSearch.getIdCourse()!=null){
                    if(appendAnd){
                        sql.append(" and");
                    }
                    sql.append(" c.id_course = ?");
                    parametersList.add(studentSearch.getIdCourse());
                    appendAnd=true;
                }
                if(studentSearch.getIdTeacher()!=null){
                    if (appendAnd) {
                        sql.append(" and");
                    }
                    sql.append(" t.id_teacher=?");
                    parametersList.add(studentSearch.getIdTeacher());
                    appendAnd=true;
                }
                if(studentSearch.getKeyword()!=null){
                    if(appendAnd){
                        sql.append(" and");
                    }
                    studentSearch.setKeyword("%"+studentSearch.getKeyword()+"%");
                    sql.append(" c.desc like ?");
                    parametersList.add(studentSearch.getKeyword());
                    appendAnd=true;
                }




            }


            con = DbUtil.getConnection();
            ps = con.prepareStatement(sql.toString());
            if (parametersList!=null){
                for (int i = 0; i < parametersList.size(); i++) {
                    ps.setObject(i+1, parametersList.get(i));
                }
            }

            System.out.println(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id_student"));
                student.setFirstName(rs.getString("s_first_name"));
                student.setLastName(rs.getString("s_last_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());

                Course course = new Course();
                course.setId(rs.getInt("id_course"));
                course.setName(rs.getString("course_name"));
                course.setDesc(rs.getString("desc"));
                course.setDuration(rs.getInt("duration"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id_teacher"));
                teacher.setFirstName(rs.getString("t_first_name"));
                teacher.setLastName(rs.getString("t_last_name"));

                teacher.setCourse(course);
                student.setTeacher(teacher);

                list.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, rs, ps);
        }

        return list;
    }
}
