<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page import="java.util.Enumeration"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut Page</title>
    </head>
    <body>
        <%@include file="logo.html"%>
        <%
          String username=(String)session.getAttribute("username");
          System.out.println("inside checkout username is "+username);
          if(username==null)
          {
             response.sendRedirect("accessdenied.html");
                      
          }
          else
          {
              String totalAmount=request.getParameter("totalAmount");
              StringBuffer displayBlock=new StringBuffer("<h1>My Store-CheckOut Page</h1>");
              displayBlock.append("<div style='float: left;'>");
              displayBlock.append("<p><strong>Thank You For Shopping With Us!</strong></p>");
              displayBlock.append("<p><strong>Your payment of Rs "+totalAmount+" is under processing!</strong></p>");
             
              Enumeration en=session.getAttributeNames();
              ArrayList<ItemDTO> itemList=new ArrayList<ItemDTO>();
              while(en.hasMoreElements()){
                Object o=en.nextElement();
                if(o.equals("username")==false){
                     ItemDTO item=(ItemDTO)session.getAttribute(o.toString());
                     itemList.add(item);
                     session.removeAttribute(o.toString());
                }  
              }
              try
              {
              StoreDAO.addOrder(username, itemList, Double.parseDouble(totalAmount));
              displayBlock.append("<p><strong>Order saved in the database:</strong></p>");
              }
              catch(Exception e){
                  System.out.println("Exception from StoreModel.addOrder:"+e);
              }
              displayBlock.append("<p><a href='StoreControllerServlet'>Shop Again</a>&nbsp;&nbsp;&nbsp;&nbsp;");
              displayBlock.append("<a href='myorders.jsp'>My Orders</a></p></div>");
              displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
              out.println(displayBlock);
          }
        %>
       </body>
</html>
