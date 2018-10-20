<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">

    $('#idSelectCourse').val('${teacher.course.id}');

</script>

<span>First nane:</span>
<br>
<input type="text" id="idInputTeacherFirstName" value="${teacher.firstName}">
<br>
<br>
<span>Last nane:</span>
<br>
<input type="text" id="idInputTeacherLastName" value="${teacher.lastName}">
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

<input type="hidden" id="teacherId" value="${teacher.id}">
