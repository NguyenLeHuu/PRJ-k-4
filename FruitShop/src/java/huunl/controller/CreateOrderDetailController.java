/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.controller;

import huunl.order.OrderDAO;
import huunl.order.OrderDTO;
import huunl.orderDetail.OrderDetailDAO;
import huunl.orderDetail.OrderDetailDTO;
import huunl.product.FruitDAO;
import huunl.shopping.Cart;
import huunl.shopping.Fruit;
import huunl.users.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CreateOrderDetailController", urlPatterns = {"/CreateOrderDetailController"})
public class CreateOrderDetailController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SendEmailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {

                    int orderID = (int) request.getAttribute("orderID");
                    Random random = new Random();
                    for (Fruit fruit : cart.getCart().values()) {

                        double price = fruit.getPrice();
                        int quantity = fruit.getQuantity();
                        String productID = fruit.getId();
                        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
                        FruitDAO fruitDao = new FruitDAO();
                        int detailID=-1;
                        boolean checkDuplicate = true;
                        while(checkDuplicate){
                            detailID = random.nextInt(10000) + 1;
                            checkDuplicate = orderDetailDao.checkDuplicate(detailID);
                        }
                        OrderDetailDTO orderDetail = new OrderDetailDTO(detailID, price, quantity, orderID, productID);
                        
                        if (!checkDuplicate) {
                            boolean checkCreate = orderDetailDao.createOrderDetail(orderDetail);
                            int quantityDB = fruitDao.getQuantityFromDB(productID);
                            if (quantityDB > 0) {
                                int newQuantity = quantityDB - quantity;
                                boolean checkUpdateQuantity = fruitDao.updateQuantity(productID, newQuantity);
                                if (checkCreate && checkUpdateQuantity) {
                                    url = SUCCESS;
                                }
                            }
                        }
                    }
                    cart.cleanCart();
                    request.setAttribute("ORDER_SUCCESS", "Order Successfully!");
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
