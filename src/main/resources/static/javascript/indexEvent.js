const signUp = () => {
    let signUpUser = {
        email: $('#signUpEmail').val(),
        password: $('#signUpPassword').val()
    };
    let data = JSON.stringify(signUpUser);
    console.log(data);
    $.ajax({
        url: '/user/signUp',
        type: 'post',
        contentType:"application/json;charset=UTF-8",
        data: data,
        success: () => {
            // console.log("success");
            // console.log(data);
            $('#signUpEmail').val('');
            $('#signUpPassword').val('');
            $('#signUpConfirmPassword').val('');
            $('#signUpModal').modal('toggle');
            alert("Sign up success");
        },
        error: (err) => {
            console.log(err);
            alert("Sign up failed");
        }
    });
    return false;
};
$('#signUpForm').submit(signUp);