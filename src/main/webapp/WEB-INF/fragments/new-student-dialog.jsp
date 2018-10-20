<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#idSelectCourse').change(function () {
            var idCourse = $(this).val();

            if (idCourse.trim().length == 0) {
                $('#idSelectTeacher').hide();
                return;
            }
            $.ajax({
                url: '/ts?action=getTeachersByCourseId',
                data: 'id=' + idCourse,
                dataType: 'json',
                success: function (data) {
                    $("#idSelectTeacher").empty();
                    $("#idSelectTeacher").append(new Option('Select teacher...', ''));
                    data.forEach(function (t) {

                        $("#idSelectTeacher").append(new Option(t.firstName + ' ' + t.lastName, t.id));

                    });

                    $("#idSelectTeacher").show();
                }
            });
        });
    });
</script>

<span>First nane:</span>
<br>
<input type="text" id="idInputStudentFirstName">
<br>
<br>
<span>Last nane:</span>
<br>
<input type="text" id="idInputStudentLastName">
<br>
<br>
<span>Date of birth:</span>
<br>
<input type="date" id="idInputStudentDob">
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
<span>Teacher:</span>
<br>
<select id="idSelectTeacher" hidden>

</select>