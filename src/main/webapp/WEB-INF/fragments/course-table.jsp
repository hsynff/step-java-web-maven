<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.step.course.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {
        $('#idTableCourse').DataTable();
    });
</script>

<div style="text-align: center"><h1>Course Data</h1></div>

<table id="idTableCourse" class="display cell-border">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Duration</th>
        <c:if test="${sessionScope.user ne null && sessionScope.user.role.id == 1}">
        <th>Delete</th>
        </c:if>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="course" items="${courseList}">
        <tr>
            <td>${course.id}</td>
            <td>${course.name}</td>
            <td>${course.desc}</td>
            <td>${course.duration}</td>
            <c:if test="${sessionScope.user ne null && sessionScope.user.role.id == 1}">
            <td><a href="#" onclick="deleteCourse('${course.id}')" style="text-align: center">
                <i class="fas fa-user-times" style="display: block"></i>
            </a></td>
            </c:if>
            <td><a href="#" onclick="openEditDialog('${course.id}', 'getEditCourseJsp', 'Edit course')" style="text-align: center">
                <i class="fas fa-edit" style="display: block"></i>
            </a></td>
        </tr>
    </c:forEach>


    </tbody>
</table>