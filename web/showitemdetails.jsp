<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
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

                StringBuffer displayBlock = new StringBuffer("<h1>My store-item Details</h1>");
                ItemDTO item = (ItemDTO) request.getAttribute("itemdesc");

                displayBlock.append("<strong><a href='StoreControllerServlet'>" + item.getItemType()
                        + "&gt;</a>" + item.getItemName() + "</strong></p>");

                displayBlock.append("<div style='float left;'>");

                displayBlock.append("<img src= \'images/" + item.getItemImage() + "'>"
                        + "</div>");

                displayBlock.append("<div style='float left;padding-left:12px'>");
                displayBlock.append("<p><strong>Description:</strong><br>"
                        + item.getItemDesc()
                        + "</p>");
                displayBlock.append("<p><strong>Price:</strong><br>"
                        + item.getItemPrice()
                        + "</p>");
                
                System.out.print("asssaasa"+item.getItemId());
                
                displayBlock.append("<p><a href='addtocart.jsp?itemid="
                        +item.getItemId()
                        +"'>Add to Cart"
                        + "</a></p></div> ");
                
                displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>Myorders</a>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;"
                        + "<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);

            }
        %>
    </body>
</html>


