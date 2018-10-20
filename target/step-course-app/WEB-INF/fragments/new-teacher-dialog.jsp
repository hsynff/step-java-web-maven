<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<span>First nane:</span>
<br>
<input type="text" id="idInputTeacherFirstName">
<br>
<br>
<span>Last nane:</span>
<br>
<input type="text" id="idInputTeacherLastName">
<br>
<br>
<span>Course :</span>
<br>
<select id="idSelectCourse">
    <option value="">Select course...</option>
    <c:forEach var="course" items="${courseList}">
        <option value="${course.id}">${course.name}</option>
    </c:forEach>
</select>
<br><br>
