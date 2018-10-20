<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student - search</title>
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/external/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#idTableStudent').DataTable();

            $('#studentName').val('${param.studentName}');
            $('#studentSurname').val('${param.studentSurname}');
            $('#dobMin').val('${param.dobMin}');
            $('#dobMax').val('${param.dobMax}');
            $('#idCourse').val('${param.idCourse}');
            $('#courseDurationMin').val('${param.courseDurationMin}');
            $('#courseDurationMax').val('${param.courseDurationMax}');
            $('#idTeacher').val('${param.idTeacher}');
            $('#keyword').val('${param.keyword}');
        }
        );
    </script>
</head>
<body>


<div class="search-page-content">
    <a href="${pageContext.request.contextPath}/">Home page</a>

    <form action="/ss" method="GET">
        <input type="hidden" name="action" value="search">
        <table cellpadding="10">
            <tbody>
            <tr>
                <td>
                    <span>Student name:</span>
                    <br>
                    <input type="text" name="studentName" id="studentName">
                </td>
                <td>
                    <span>Student surname</span>
                    <br>
                    <input type="text" name="studentSurname" id="studentSurname">
                </td>
                <td>
                    <span>Student Date of birth (min.)</span>
                    <br>
                    <input type="date" name="dobMin" id="dobMin">
                </td>
                <td>
                    <span>Student Date of birth (max.)</span>
                    <br>
                    <input type="date" name="dobMax" id="dobMax">
                </td>
                <td>
                    <span>Course:</span>
                    <br>
                    <select name="idCourse" id="idCourse">
                        <option value="">Any course...</option>
                        <c:forEach var="course" items="${courseList}">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <span>Course duration (min.)</span>
                    <br>
                    <input type="number" name="courseDurationMin" id="courseDurationMin">
                </td>
                <td>
                    <span>Course duration (max.)</span>
                    <br>
                    <input type="number" name="courseDurationMax" id="courseDurationMax">
                </td>
                <td>
                    <span>Teacher:</span>
                    <br>
                    <select name="idTeacher" id="idTeacher">
                        <option value="">Any teacher...</option>
                        <c:forEach var="teacher" items="${teacherList}">
                            <option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <span>Keyword:</span>
                    <br>
                    <input type="text" name="keyword" id="keyword">
                </td>
                <td>
                    <input type="submit" value="Search" >
                    <input type="reset" value="Reset" >
                </td>
            </tr>
            </tbody>
        </table>
    </form>


    <table id="idTableStudent" class="display cell-border">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Date of Birth</th>
            <th>Course</th>
            <th>Course Description</th>
            <th>Course Duration</th>
            <th>Teacher</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="student" items="${resultList}">

        <tr>
        <td>${student.id}</td>
        <td>${student.firstName}</td>
        <td>${student.lastName}</td>
        <td>${student.dateOfBirth}</td>
        <td>${student.teacher.course.name}</td>
        <td>${student.teacher.course.desc}</td>
        <td>${student.teacher.course.duration}</td>
        <td>${student.teacher.firstName} ${student.teacher.lastName}</td>

        </c:forEach>



        </tbody>
    </table>
</div>
</body>
</html>
