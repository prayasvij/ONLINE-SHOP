<%-- 
    Document   : placeorder
    Created on : Jan 9, 2019, 4:04:13 AM
    Author     : Vijayvargiya
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="shoppingcatalog.dto.viewOrderDTO"%>
<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="logo.html"%>
        <%
            String username = (String) session.getAttribute("username");
            if (username == null) {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            } else {
                String OrderId = (String) request.getParameter("ID");
                System.out.println("orderId got:" + OrderId);

                StringBuffer displayBlock = new StringBuffer("<h1>My store-item Details</h1>");
                displayBlock.append("<div style='float:left;'>");

                displayBlock.append("<table border='1'>");
                displayBlock.append("<tr><th>ItemName</th><th>ItemPrice</th></tr>");
                double totalAmount = 0.0;
                ArrayList<viewOrderDTO> arraylist = StoreDAO.getOrdersById(OrderId);
                for (viewOrderDTO item : arraylist) {
                    displayBlock.append("<tr><td>" + item.getItemName() + "</td><td>"
                            + item.getItemPrice() + "</td></tr>");
                    totalAmount += item.getItemPrice();
                }

                displayBlock.append("</table>");
                displayBlock.append("<p><strong>Total Amount To Pay:</strong>" + totalAmount + "</p>");

                displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>Myorders</a>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;"
                        + "<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);

            }


        %>
    </body>
</html>

<%         String OrderId = (String) request.getParameter("ID");

    System.out.println(
            "orderId got:" + OrderId);
%>
