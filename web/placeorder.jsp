<%-- 
    Document   : placeorder
    Created on : Jan 9, 2019, 4:04:13 AM
    Author     : Vijayvargiya
--%>

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
                    StringBuffer displayBlock = new StringBuffer("<h1>My store-item Details</h1>");
                    displayBlock.append("<div style='float:left;'>");
                    Enumeration en = session.getAttributeNames();
                    
//                    String itemid=request.getParameter("id");
                    
//                    if(itemid==item.getItemId){
//                       //remove the object
//                    }else{
//                        
//                    }



                    displayBlock.append("<table border='1'>");
                    displayBlock.append("<tr><th>ItemName</th><th>ItemPrice</th></tr>");
                    double totalAmount = 0.0;
                    
                    while (en.hasMoreElements()) {
                        Object o = en.nextElement();
                        if (o.equals("username") == false) {

                            ItemDTO item = (ItemDTO) session.getAttribute(o.toString());

                            displayBlock.append("<tr><td>" + item.getItemName() + "</td><td>"
                                    + item.getItemPrice() + "</td><td><a href='placeorder.jsp?id="+item.getItemId()+"' style='color:red'>remove</a></td> </tr>");
                            totalAmount += item.getItemPrice();
                        }
                    }
                    displayBlock.append("</table>");
                    displayBlock.append("<p><strong>Total Amount To Pay:</strong>" + totalAmount + "</p>");
                    
                    displayBlock.append("<p><a href='StoreControllerServlet'>"
                            + "Continue Shopping</a>&nbsp;&nbsp;&nbsp;&nbsp;");
                    displayBlock.append("<a href='checkout.jsp?totalAmount=" + totalAmount + "'>CheckOut</a></p></div>");

                    //item name
//                     item.getItemName();
                    //item prize
//                     item.getItemPrice();
                    // total:
//                     totalAmount+=item.getItemPrice();
                    displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>Myorders</a>"
                            + "&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                    out.println(displayBlock);

                }
            
        
        %>
    </body>
</html>
