<%@page import="java.text.SimpleDateFormat"%>
<%@page import="shoppingcatalog.dto.OrderDTO"%>
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
            String username = (String) session.getAttribute("username");
            System.out.println("inside checkout username is " + username);
            if (username == null) {
                response.sendRedirect("accessdenied.html");

            } else {
                StringBuffer displayBlock = new StringBuffer("<h1>My Store-My Orders</h1>");

                ArrayList<OrderDTO> orderList = StoreDAO.getOrdersByCustomer(username);
                if (orderList.isEmpty()) {
                    displayBlock.append("You have not placed any order yet");
                } else {

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
displayBlock.append("<table border='1'>");
                    displayBlock.append("<tr><td> OrderID</td><td>orderAmount</td><td> OrderDAte </td></tr>");
                    for (OrderDTO o : orderList) {
                        String dateStr = sdf.format(o.getOrderDate());
                     displayBlock.append("<tr><td>" + o.getOrderid()+ "</td><td>"
                                    + o.getOrderAmount()+ "</td><td>"+dateStr+"</td><td><a href='viewOrder.jsp?ID="+o.getOrderid()+"'>view</a></td></tr>");                         
                     
                    }
                    
                    displayBlock.append("</table>");
                    
                    displayBlock.append("<p><a href='StoreControllerServlet'>Show Categories</a></p></div>");
                    displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                    out.println(displayBlock);
                }
            }
        %>
    </body>
</html>
