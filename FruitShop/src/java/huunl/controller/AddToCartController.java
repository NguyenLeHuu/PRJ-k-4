/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.controller;

import huunl.shopping.Cart;
import huunl.shopping.Fruit;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private final static String ERROR = "error.jsp";
    private final static String SUCCESS = "UserSearchController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url = ERROR;
         try {
//            String teaString = request.getParameter("cmbTea");
//            String tmp[] = teaString.split("-");
//            String id = tmp[0];
//            String name = tmp[1];
//            double price = Double.parseDouble(tmp[2]);
//            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String search = request.getParameter("search");
            String id = request.getParameter("productID");
            String name = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            Fruit fruit = new Fruit(id, name, quantity, price);
             HttpSession session = request.getSession();
             Cart cart = (Cart)session.getAttribute("CART");
             if(cart == null){
                 cart = new Cart();
             }
             cart.add(fruit);
             session.setAttribute("CART", cart);
             url = SUCCESS;
             String message = "Ban vua chon "+quantity+" "+name+" thanh cong !";
             request.setAttribute("SHOPPING_MESSAGE", message);
        } catch (Exception e) {
            log("Error at AddToCartController: "+ e.toString());
        }finally{
             request.getRequestDispatcher(url).forward(request, response);
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
