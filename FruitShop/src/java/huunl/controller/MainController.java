/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH = "SearchController";
    private static final String LOGOUT = "LogoutController";
    private static final String UPDATE = "UpdateUserController";
    private static final String CREATE = "CreateUserController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String UPDATE_CART = "UpdateCartController";
    private static final String REMOVE_CART = "RemoveCartController";
    private static final String DELETE_FRUIT = "DeleteFruitController";
    private static final String UPDATE_FRUIT = "UpdateFruitController";
    private static final String CREATE_FRUIT = "CreateFruitController";
    private static final String USER_SEARCH = "UserSearchController";
    private static final String CHECK_OUT = "CheckOutController";
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if("Login".equals(action)) {
                url = LOGIN;
            }else if("Search".equals(action)) {
                url = SEARCH;
            }else if("Search Fruit".equals(action)) {
                url = USER_SEARCH;
            }else if("Logout".equals(action)) {
                url = LOGOUT;            
            }else if("Update".equals(action)) {
                url = UPDATE;
            }else if("Register".equals(action)) {
                url = CREATE;
            }else if("Add to cart".equals(action)) {
                url = ADD_TO_CART;
            }else if("View cart".equals(action)) {
                url = VIEW_CART;
            }else if("Modify".equals(action)) {
                url = UPDATE_CART;
            }else if("Remove".equals(action)) {
                url = REMOVE_CART;
            }else if("Delete Fruit".equals(action)) {
                url = DELETE_FRUIT;
            }else if("Update Fruit".equals(action)) {
                url = UPDATE_FRUIT;
            }else if("Add Fruit".equals(action)) {
                url = CREATE_FRUIT;
            }else if("Check Out".equals(action)) {
                url = CHECK_OUT;
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_MESSAGE", "function is not avaiable!");
            }
        } catch (Exception e) {
            log("Error at MainController:"+e.toString());
        }finally {
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
