var username, password;

$(document).ready(function ()
{
    $("#registerbtn").click(function ()
    {
        connect();
    });
});
function validate()
{
    username = $("#username").val();
    password = $("#password").val();

    var status = true;
    if (username === "")
    {

        $("#username").after("<span id='error1'>Username Reqd!</span>");
        $("#error1").css("color", "red");
        status = false;
    }
    if (password === "")
    {

        $("#password").after("<span id='error2'>Password Reqd!</span>");
        $("#error2").css("color", "red");
        status = false;
    }
    $("#error1").fadeOut(4000);
    $("#error2").fadeOut(4000);
    return status;
}
function connect()
{

    if (!validate())
    {

        return;
    }

    var mydata = {username: username, password: password};
    var request = $.post("RegistrationControllerServlet", mydata, processresponse);
    request.error(handleError);
}
function processresponse(responseText)
{
    var res = responseText;
    if (res.trim() === "uap") {
        $("#regresult").text("Sorry username already present please check the details").css("color", "red");
    } else if (res.trim() === "success") {
        $("#regresult").html("Username registered succefully!!<br><a href='login.html'>LOGIN</a>");

    } else if (res.trim() === "failure") {
        $("#regresult").text("Failure in registring the user!!");
    }
    else{
        $("#regresult").text("Some problem in Server .Try Again!!!").css("color", "red");
    }
}
function handleError(xhr, textStatus)
{

    if (textStatus === 'error') {
        $("#loginresult").html("Error is " + xhr.status);
    }
}

