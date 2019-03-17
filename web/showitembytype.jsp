<%@page import="shoppingcatalog.dto.ItemInfoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*" %>
<%
            String username = (String) session.getAttribute("username");
            if (username == null) {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            } else {
                
                StringBuffer displayBlock = new StringBuffer("<ul>");
                
               List<ItemInfoDTO> itemByList =( List<ItemInfoDTO>) request.getAttribute("itemtypelist");
//               itemByList.
                for (ItemInfoDTO obj :itemByList){
                    displayBlock.append("<li id='" +obj.getItemId()+ "'><a href='StoreControllerServlet?itemId="+obj.getItemId()+"'>" + obj.getItemName() + "</a></li>");
                }
                displayBlock.append("</ul>");
                
                out.println(displayBlock);

            }


        %>