<%
    boolean result = (Boolean) request.getAttribute("result");
    System.out.println(result);
    String username = (String) request.getAttribute("username");
    String usertype = (String) request.getAttribute("usertype");
    System.out.println("username "+username+"usertype "+usertype);
    if (result == true && usertype.equalsIgnoreCase("CUSTOMER")) {
        String url = "StoreControllerServlet;jsessionid=" + session.getId();
        out.println(url);
        HttpSession sess = request.getSession();
        sess.setAttribute("username", username);

    } else if (result == true && usertype.equalsIgnoreCase("ADMIN")) {
        String url = "AdminControllerServlet;jsessionid=" + session.getId();
        out.println(url);
        HttpSession sess = request.getSession();
        sess.setAttribute("username", username);
    }
    else
    out.println("error");
%>