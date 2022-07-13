/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.controller;

import huunl.order.OrderDAO;
import huunl.order.OrderDTO;
import huunl.shopping.Cart;
import huunl.shopping.Fruit;
import huunl.users.UserDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
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
@WebServlet(name = "CreateOrderController", urlPatterns = {"/CreateOrderController"})
public class CreateOrderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "CreateOrderDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                Random random = new Random();
                int orderID = random.nextInt(10000)+1;
                LocalDateTime current = LocalDateTime.now();
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    String userID = user.getUserID();
                    double total = 0;
                    for (Fruit fruit : cart.getCart().values()) {
                        total += fruit.getQuantity() * fruit.getPrice();
                    }

                    OrderDAO dao = new OrderDAO();
                    OrderDTO order = new OrderDTO(orderID, current.toLocalDate(), total, userID);
                    boolean checkDuplicate = dao.checkDuplicate(orderID);
                    if (!checkDuplicate) {
                        boolean check = dao.createOrder(order);
                        if (check) {
                            request.setAttribute("orderID", orderID);
                            url = SUCCESS;
                        }
                    }

                }
            }
        } catch (Exception e) {
            log("Error at CreateOrderController: " + e.toString());
        } finally {
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
