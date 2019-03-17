
<%@page import="java.util.Enumeration"%>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page import="java.util.List"%>
<%@page import="shoppingcatalog.dto.ItemInfoDTO"%>
<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="scripts/showitem.js"></script>
        <script src="scripts/jquery.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <title>Store Items</title> 
    </head>
    <body>
        <%@ include file="logo.html"%>
        <%

            String username = (String) session.getAttribute("username");
            if (username == null) {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            } else {
                String itemId = request.getParameter("itemid");

                ItemDTO item = StoreDAO.getItemDetails(Integer.parseInt(itemId));
                
                String name = item.getItemName();
                session.setAttribute(String.valueOf(item.getItemId()), item);
                StringBuffer displayBlock = new StringBuffer("<h1>My store-item Details</h1>");
                displayBlock.append("<div style='float:left;'>");
                displayBlock.append("<p><strong>"
                        + "Item Added Successfully"
                        + "</strong></p>");
                
                displayBlock.append("<p><strong>"
                        + "ItemID" + "</strong>"
                        + item.getItemId()
                        + "</p>");
                displayBlock.append("<p><strong>"
                        + "ItemName" + "</strong>"
                        + item.getItemName()
                        + "</p>");
                Enumeration en = session.getAttributeNames();
                int count = 0;
                while (en.hasMoreElements()) {
                    ++count;
                    en.nextElement();
                }
                displayBlock.append("<p><strong>"
                        + "Total Items in cart" + "</strong>"
                        + (count - 1)
                        + "</p>");
                displayBlock.append("<p><strong>"
                        + "<a href='StoreControllerServlet'>"
                        + "ContinueShopping"
                        + "</a>&nbsp;&nbsp;&nbsp;&nbsp;");
                displayBlock.append("<a href='placeorder.jsp'>Place order</a></p></div>");

                displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>Myorders</a>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;"
                        + "<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
            }
        %>
    </body>
</html>
