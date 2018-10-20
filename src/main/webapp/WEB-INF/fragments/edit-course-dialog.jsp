<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<span>Course name:</span>
<br>
<input type="text" id="idInputCourseName" value="${course.name}">
<br>
<br>
<span>Description:</span>
<br>
<textarea id="idInputCourseDesc">${course.desc}</textarea>
<br>
<br>
<span>Duration :</span>
<br>
<input type="number" id="idInputCourseDuration" value="${course.duration}">
<input id="idCourse" type="hidden" value="${course.id}">
