var clickedButton;


$(document).ajaxComplete(function (a, response, b) {
    if (response.status == 401){
        window.location.href = '/ns?action=login';
    }
});

$(function () {

    $('#idButtonStudent').click(function () {
        getStudentTable();
    });

    $('#idButtonTeacher').click(function () {
        getTeacherTable();
    });

    $('#idButtonCourse').click(function () {
        getCourseTable();
    });



    $('#idButtonNewData').click(function () {

        var action;
        var title;

        switch (clickedButton) {
            case 'student':
                action = "getNewStudentJsp";
                title = "New Student";
                break;
            case 'teacher':
                action = "getNewTeacherJsp";
                title = "New Teacher";
                break;
            case 'course':
                action = "getNewCourseJsp";
                title = "New Course";
                break;
            default:
                alert('Please select action from menu');
                return;
        }
        openNewDataDialog(action, title);
    });


});


function getStudentTable() {

    $.ajax({
        url: '/ss?action=getAllStudent',
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $('#idDivStudentData').html(data);

            $('#idDivStudentData').show();
            $('#idDivTeacherData').hide();
            $('#idDivCourseData').hide();
        }
    });

    clickedButton = 'student';

}

function getTeacherTable() {

    $.ajax({
        url: '/ts?action=getAllTeacher',
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $('#idDivTeacherData').html(data);

            $('#idDivStudentData').hide();
            $('#idDivTeacherData').show();
            $('#idDivCourseData').hide();
        }
    });

    clickedButton = 'teacher';
}

function getCourseTable() {

    $.ajax({
        url: '/cs?action=getAllCourse',
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $('#idDivCourseData').html(data);

            $('#idDivStudentData').hide();
            $('#idDivTeacherData').hide();
            $('#idDivCourseData').show();
        }
    });

    clickedButton = 'course';
}

function deleteStudent(id) {
    var cond = confirm('Are you sure?');

    if (cond) {
        $.ajax({
            url: '/ss?action=deleteStudent',
            type: 'POST',
            data: 'id=' + id,
            success: function () {
                alert('Student deleted!');
                getStudentTable();
            },
            error: function () {
                alert('Error while deleting student!');
            }
        });
    }
}

function deleteTeacher(id) {
    var cond = confirm('Are you sure?');

    if (cond) {
        $.ajax({
            url: '/ts?action=deleteTeacher',
            type: 'POST',
            data: 'id=' + id,
            success: function () {
                alert('Teacher deleted!');
                getTeacherTable();
            },
            error: function () {
                alert('Error while deleting teacher!');
            }
        });
    }
}

function deleteCourse(id) {
    var cond = confirm('Are you sure?');

    if (cond) {
        $.ajax({
            url: '/cs?action=deleteCourse',
            type: 'POST',
            data: 'id=' + id,
            success: function () {
                alert('Course deleted!');
                getCourseTable();
            },
            error: function () {
                alert('Error while deleting course!');
            }
        });
    }
}

function openNewDataDialog(action, title) {
    $.ajax({
        url: '/ns?action=' + action,
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $('#idDialogNewData').html(data);
            $('#idDialogNewData').dialog({
                title: title
            });
            $('#idDialogNewData').dialog('open');
        }
    });
}


function addNewData() {
    switch (clickedButton) {
        case 'student':
            addNewStudent();
            break;
        case 'teacher':
            addNewTeacher();
            break;
        case 'course':
            addNewCourse();
            break;
        default:
            alert('Please select action from menu');
            break;
    }
}

function addNewStudent() {
    var firstName = $('#idInputStudentFirstName').val();
    var lastName = $('#idInputStudentLastName').val();
    var dob = $('#idInputStudentDob').val();
    var idTeacher = $('#idSelectTeacher').val();

    $.ajax({
        url: '/ss?action=addStudent',
        data: 'firstName='+firstName+'&lastName='+lastName+'&dob='+dob+'&idTeacher='+idTeacher,
        type: 'POST',
        success: function () {
            alert('Student added!');
            $('#idDialogNewData').dialog("close");
            getStudentTable();
        },
        error: function () {
            alert('Error while adding student!');
        }

    });
}

function addNewTeacher() {
    var firstName = $('#idInputTeacherFirstName').val();
    var lastName = $('#idInputTeacherLastName').val();
    var course = $('#idSelectCourse').val();

    $.ajax({
       url: '/ts?action=addTeacher',
       data: 'firstName=' + firstName + '&lastName=' + lastName + '&course=' + course,
        type: 'POST',
        success: function () {
            alert('Teacher Added!');

            $('#idDialogNewData').dialog("close");
            getTeacherTable();
        },
        error: function () {
            alert('Error while adding teacher!');
        }
    });
}

function addNewCourse() {
    var name = $('#idInputCourseName').val();
    var desc = $('#idInputCourseDesc').val();
    var duration = $('#idInputCourseDuration').val();

    $.ajax({
        url: '/cs?action=addCourse',
        data: 'name='+name+'&desc='+desc+'&duration='+duration,
        type: 'POST',
        success: function () {
            alert('Course Added!');

            $('#idDialogNewData').dialog("close");
            getCourseTable();
        },
        error: function () {
            alert('Error while adding course!');
        }
    });
}

// function openEditStudentDialog(idStudent) {
//     $.ajax({
//         url: '/ns?action=getEditStudentJsp',
//         data: 'id='+idStudent,
//         dataType: 'html',
//         type: 'GET',
//         success: function (data) {
//             $('#idDialogEditData').html(data);
//             $('#idDialogEditData').dialog('open');
//         }
//     });
// }
//
// function openEditTeacherDialog(idTeacher) {
//     $.ajax({
//         url: '/ns?action=getEditTeacherJsp',
//         data: 'id='+idTeacher,
//         dataType: 'html',
//         type: 'GET',
//         success: function (data) {
//             $('#idDialogEditData').html(data);
//             $('#idDialogEditData').dialog('open');
//         }
//     });
// }

function openEditDialog(id, action, title) {
    $.ajax({
        url: '/ns?action=' + action,
        data: 'id='+id,
        dataType: 'html',
        type: 'GET',
        success: function (data) {

            $('#idDialogEditData').html(data);
            $('#idDialogEditData').dialog('open');
            $('#idDialogEditData').dialog({title:title});
        }
    });
}



function editData() {
    switch (clickedButton) {
        case 'student':
            editStudent();
            break;
        case 'teacher':
            editTeacher();
            break;
        case 'course':
            editCourse();
            break;
        default:
            alert('Please select action from menu');
            break;
    }
}

function editStudent() {
    var firstName = $('#idInputStudentFirstName').val();
    var lastName = $('#idInputStudentLastName').val();
    var dob = $('#idInputStudentDob').val();
    var idTeacher = $('#idSelectTeacher').val();
    var idStudent = $('#idInputStudentId').val();

    $.ajax({
        url: '/ss?action=editStudent',
        data: 'firstName='+firstName+'&lastName='+lastName+'&dob='+dob+'&idTeacher='+idTeacher+'&idStudent='+idStudent,
        type: 'POST',
        success: function () {
            alert('Student edited!');
            $('#idDialogEditData').dialog("close");
            getStudentTable();
        },
        error: function () {
            alert('Error while editing student!');
        }

    });
}

function editTeacher() {
    var firstName = $('#idInputTeacherFirstName').val();
    var lastName = $('#idInputTeacherLastName').val();
    var idTeacher = $('#teacherId').val();
    var idCourse = $('#idSelectCourse').val();

    $.ajax({
        url: '/ts?action=editTeacher',
        data: 'firstName='+firstName+'&lastName='+lastName+'&idCourse=' + idCourse + '&teacherId=' + idTeacher,
        type: 'POST',
        success: function () {
            alert('Teacher edited!');
            $('#idDialogEditData').dialog("close");
            getTeacherTable();
        },
        error: function () {
            alert('Error while editing teacher!');
        }

    });
}

function editCourse() {
    var courseId = $('#idCourse').val();
    var courseName = $('#idInputCourseName').val();
    var courseDesc = $('#idInputCourseDesc').val();
    var courseDuration = $('#idInputCourseDuration').val();
    $.ajax({
        url: '/cs?action=editCourse',
        data: 'name='+courseName+'&id='+courseId+'&desc=' + courseDesc + '&duration=' + courseDuration,
        type: 'POST',
        success: function () {
            alert('Course edited!');
            $('#idDialogEditData').dialog("close");
            getCourseTable();
        },
        error: function () {
            alert('Error while editing Course!');
        }

    });
}




