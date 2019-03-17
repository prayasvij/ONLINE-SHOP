//   var isRegChecked,isLogChecked;
$(document).ready(function ()
{
    $('#nextbtn').click(function(){
        dotask();
    });
});
function dovalidate(){

    isRegChecked=$('#rbtnReg').is(':checked');
    isLogChecked=$('#rbtnLog').is(':checked');
    var status =true;
    if(isRegChecked===false&&isLogChecked===false)
    {
        $("#result").text("please select on option first");
        $("#result").css({"font-weight":"bold","color":"red"});
        $("#result").fadeOut(4000);
        status=false;
    }
    return status;
}
function dotask(){
//    alert("dotask");-

   var ans= dovalidate();
   if(ans===false){
       return;
   }
   
   if(isRegChecked===true){
       window.location="registration.html";
   }
   else if(isLogChecked===true){
       window.location="login.html";
   }
   
}