/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.dao.StoreDAO;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;

/**
 *
 * @author Vijayvargiya
 */
public class StoreControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("username");
        RequestDispatcher rd = null;
        System.out.println("Uname" + uname);
        try {
            if (uname == null) {
                System.out.println("usenam null" + uname);
                session.invalidate();
                rd = request.getRequestDispatcher("accessdenied.html");
            } else {
                String itemId = (request.getParameter("itemId"));
                String itemType = request.getParameter("itemType");
                System.out.println("usename not null" + uname);

                if (itemId == null && itemType == null) {
                    System.out.println(" itemType null item type=" + itemType);
                    List<String> itemList = StoreDAO.getItemTypes();
                    request.setAttribute("itemlist", itemList);
                    rd = request.getRequestDispatcher("seestore.jsp");
//
                } else if (itemType != null) {
                    System.out.println(" itemType not null item type=" + itemType);

                    List<ItemInfoDTO> itemList = StoreDAO.getItemByType(itemType);
                    request.setAttribute("itemtypelist", itemList);
                    rd = request.getRequestDispatcher("showitembytype.jsp");
//
////               {getByItemTypes()-storeDAO
                } else if (itemId != null) {

                    System.out.println(" itemid not null item id=" + itemId);
                    ItemDTO obj = new ItemDTO();
                    obj = StoreDAO.getItemDetails(Integer.parseInt(itemId));
                    request.setAttribute("itemdesc", obj);
//
                    rd = request.getRequestDispatcher("showitemdetails.jsp");
//
//                    //                     {getItemDetails()-storeDAO->showitemsdetails.jsp}                                
                }
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION:" + e);
            request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("showexception.jsp");
        } finally {
            rd.forward(request, response);
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
