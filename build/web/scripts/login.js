var uname, pwd, utype;
function connect() {
    if (validate() === false) {
        return;
    }
//ajax code
    var data = {username: uname, password: pwd, usertype: utype};
    var rqst = 'LoginControllerServlet';
    var request = $.post(rqst, data, processResponse);
    request.error(handleError);
}
function cleartext() {
    $("#loginresult").text("");
}
function validate() {
    cleartext();
//    get the value
    uname = $("#username").val();
    utype = $("#usertype").val();
    pwd = $("#password").val();
//check the validation
    var status = true;
    if (uname === "" || pwd === "") {
//    alert for uname
        $("#loginresult").text("Please enter complete details....").css("color", "red");
        setTimeout(cleartext, 3000);

        status = false;
    }
//    if (pwd === "") {
//        //    alert for pwd   
//        $("#loginresult").text("password is missing").css("color", "red").appendTo();
//        setTimeout(cleartext, 3000);
//        status = false;
//    }
    return status;
}
function handleError(responseText, textStatus, xhr) {
    if (textStatus === 'error') {
        $("#loginresult").text('An error occured during your code' + xhr.status).css("color", "red");
        setTimeout(cleartext, 3000);
    }
}
function processResponse(responseText) {
       var resp=responseText;
        var pagename;
        resp=resp.trim();
        if(resp.indexOf("jsessionid")!==-1)
        {
            //alert("inside success");
            $("#loginresult").css("color","green");
            if(usertype==="ADMIN")
                pagename="Options";
            else
                pagename="Store";
            $("#loginresult").html("Login Accepted!Redirecting to the "+pagename+" Page!");
            url=resp;
            setTimeout(redirectuser,3000);
        }
        else if(resp==="error")
        {
            //alert("inside error");
            $("#loginresult").css("color","red");
            $("#loginresult").html("Login Rejected");
        }
        else
        {
            //alert("inside else:"+resp);
            $("#loginresult").css("color","red");
            $("#loginresult").html("Some error occurred at the server. Try later");
            
        }   

}
function redirectuser()
{
    window.location=url;
}