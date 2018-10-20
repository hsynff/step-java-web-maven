<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.step.course.model.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {
        $('#idTableTeacher').DataTable();
    });
</script>

<div style="text-align: center"><h1>Teacher Data</h1></div>

<table id="idTableTeacher" class="display cell-border">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Course name</th>
        <th>Course duration</th>
<c:if test="${sessionScope.user ne null && sessionScope.user.role.id == 1}">
        <th>Delete</th>
</c:if>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>

<c:forEach var="teacher" items="${teacherList}">
    <tr>
        <td>${teacher.id}</td>
        <td>${teacher.firstName}</td>
        <td>${teacher.lastName}</td>
        <td>${teacher.course.name}</td>
        <td>${teacher.course.duration}</td>
        <c:if test="${sessionScope.user ne null && sessionScope.user.role.id == 1}">
        <td><a href="#" onclick="deleteTeacher('${teacher.id}')" style="text-align: center">
            <i class="fas fa-user-times" style="display: block"></i>
        </a></td>
        </c:if>
        <td><a href="#" onclick="openEditDialog('${teacher.id}', 'getEditTeacherJsp', 'Edit Teacher')" style="text-align: center">
            <i class="fas fa-edit" style="display: block"></i>
        </a></td>
    </tr>
</c:forEach>


    </tbody>
</table>
