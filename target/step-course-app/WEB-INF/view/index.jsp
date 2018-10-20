<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Joshgun
  Date: 9/9/2018
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/external/jquery-3.3.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#idDialogNewData").dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                autoOpen: false,
                buttons: {
                    "Add": function () {
                        addNewData();
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                }
            });
            $("#idDialogEditData").dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                autoOpen: false,
                buttons: {
                    "Edit": function () {
                        editData();
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
    </script>
</head>
<body>
<div class="header">

    <div style="margin-left: 50px;">
        <c:if test="${sessionScope.user ne null && sessionScope.user.role.id == 1}">
        <button id="idButtonNewData" class="header-button" title="Add new  data"><i class="fas fa-address-card"></i>
        </button>
        </c:if>
        <a style="float: right; color: red" href="/us?action=logout">Logout</a>
    </div>



</div>
<div class="menu">

    <button id="idButtonStudent" class="menu-button">Student</button>
    <button id="idButtonTeacher" class="menu-button">Teacher</button>
    <button id="idButtonCourse" class="menu-button">Course</button>

</div>


<div class="main">
    <div style="margin: 20px">

        <div id="idDivStudentData" hidden>
        </div>
        <div id="idDivTeacherData" hidden>
        </div>
        <div id="idDivCourseData" hidden>
        </div>

        <div id="idDialogNewData"></div>
        <div id="idDialogEditData"></div>


    </div>

</div>
<div class="footer">
    <span class="footer-watermark">STEP IT Academy. Copyright 2018 (c)</span>
</div>

</body>
</html>
