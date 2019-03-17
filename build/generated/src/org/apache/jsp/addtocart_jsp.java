package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Enumeration;
import shoppingcatalog.dto.ItemDTO;
import java.util.List;
import shoppingcatalog.dto.ItemInfoDTO;
import shoppingcatalog.dao.StoreDAO;

public final class addtocart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/logo.html");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <script src=\"scripts/showitem.js\"></script>\n");
      out.write("        <script src=\"scripts/jquery.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"styles/stylesheet.css\">\n");
      out.write("        <title>Store Items</title> \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"logo\">\r\n");
      out.write("         <img src=\"images/shopping_logo5.png\" >\r\n");
      out.write("</div>");
      out.write("\n");
      out.write("        ");


            String username = (String) session.getAttribute("username");
            if (username == null) {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            } else {
                String itemId = request.getParameter("itemId");

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
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
