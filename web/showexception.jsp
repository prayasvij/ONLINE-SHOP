<%
    Exception e=(Exception)request.getAttribute("exception");
    String msg=e.getMessage();
    System.out.println("Exception is "+e);
    e.printStackTrace();
    out.println(msg);
%>