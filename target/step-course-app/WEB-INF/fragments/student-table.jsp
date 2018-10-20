<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.step.course.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {
        $('#idTableStudent').DataTable();
    });
</script>

<div style="text-align: center"><h1>Student Data</h1></div>
<a href="${pageContext.request.contextPath}/ss?action=search">Advanced search</a>

<table id="idTableStudent" class="display cell-border">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Date of Birth</th>
        <th>Course</th>
        <th>Teacher</th>
        <c:if test="${sessionScope.user ne null && sessionScope.user.role.id == 1}">
        <th>Delete</th>
        </c:if>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody>

<c:forEach var="student" items="${studentList}">

    <tr>
        <td>${student.id}</td>
        <td>${student.firstName}</td>
        <td>${student.lastName}</td>
        <td>${student.dateOfBirth}</td>
        <td>${student.teacher.course.name}</td>
        <td>${student.teacher.firstName} ${student.teacher.lastName}</td>
        <c:if test="${sessionScope.user ne null && sessionScope.user.role.id == 1}">

        <td><a href="#" onclick="deleteStudent('${student.id}')" style="text-align: center">
            <i class="fas fa-user-times" style="display: block"></i>
        </a></td>
        </c:if>

        <td><a href="#" onclick="openEditDialog('${student.id}', 'getEditStudentJsp', 'Edit Student')" style="text-align: center">
            <i class="fas fa-edit" style="display: block"></i>
        </a></td>
    </tr>


</c:forEach>




    </tbody>
</table>