$(document).ready(function ()
{
    $(".productlist").hide();
    $(".userslist").hide();
    $(".viewslist").hide();
   
      $("#addform").hide();
    $("#updateform").hide();
    $("#deleteform").hide();
    test();
});

function hidefn(){
  
}
function products() {
    var para = document.getElementById("Products");

    var span = para.getElementsByTagName("span")[0];
    var spantext = span.innerHTML.trim();

    if (spantext.indexOf("+") !== -1) {

        span.innerHTML = "-" + "Products";
        $(".productlist").show();
    } else if (spantext.indexOf("-") !== -1) {
        span.innerHTML = "+" + "Products";

        $(".productlist").hide();

        return;
    }

}
function users() {
    var para = document.getElementById("Users");

    var span = para.getElementsByTagName("span")[0];
    var spantext = span.innerHTML.trim();
    if (spantext.indexOf("+") !== -1) {
        span.innerHTML = "-" + "Users";
        $(".userslist").show();
    } else if (spantext.indexOf("-") !== -1) {
        span.innerHTML = "+" + "Users";

        $(".userslist").hide();

        return;
    }

}
function views() {
    var para = document.getElementById("Views");

    var span = para.getElementsByTagName("span")[0];
    var spantext = span.innerHTML.trim();
    if (spantext.indexOf("+") !== -1) {
        span.innerHTML = "-" + "Views";
        $(".viewslist").show();
    } else if (spantext.indexOf("-") !== -1) {
        span.innerHTML = "+" + "Views";

        $(".viewslist").hide();


        return;
    }
}
function addproduct(){
     $("#addform").show(300);
      $("#updateform").hide();
    $("#deleteform").hide();
    
}
function addprd (){
     var name=$("#addpdname").val();
     var type=$("#addpdtype").val();
     var price=$("#addpdprice").val();
     var desc=$("#addpddesc").val();
//     $("#addpdimage").val();
     
     alert(name+type+price+desc);
}
function updateproduct(){
         $("#updateform").show(300);
          $("#addform").hide();
    
    $("#deleteform").hide();
}
function deleteproduct(){
         $("#deleteform").show(300);
          $("#addform").hide();
    $("#updateform").hide();
    
}

function test(){
    $('<option>').val('999').text('999').appendTo('#productid');
}
