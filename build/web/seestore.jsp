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
                StringBuffer displayBlock = new StringBuffer("<h1>My Category</h1><p>select a category to see items.</p>");
                List<String> itemList = (List<String>) request.getAttribute("itemlist");
                for (String itemType : itemList){
                    displayBlock.append("<p id='" + itemType + "'>"
                            + "<strong>"
                            + "<a href='#' onclick=getItemNames('"+ itemType + "')>"
                                    + "<span>+" +itemType + "</span>"
                                    + "</a>"
                                    + "</strong>"
                                    + "</p>");
                }
                displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>Myorders</a>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;"
                        + "<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
            }


        %>
    </body>
</html>
