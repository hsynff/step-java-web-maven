function validate() {
    var result = true;
    var inputs = $('.valid');

    var pass1 = $('#idInputPass1');
    var pass2 = $('#idInputPass2');

    if (pass1.length != 0 && pass2.length != 0) {

        pass1.removeClass("error");
        pass2.removeClass("error");
        console.log(pass1.val() != pass2.val());
        if (pass1.val() != pass2.val() || (pass1.val().trim().length == 0 || pass2.val().trim().length == 0)) {
            result = false;
            pass1.addClass("error");
            pass2.addClass("error");
        }

    }
    inputs.each(function () {
        $(this).removeClass("error");
        if ($(this).val().trim().length == 0) {
            result = false;
            $(this).addClass("error");
        }
    });


    return result;
}