/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huunl.controller;

import huunl.product.FruitDAO;
import huunl.product.FruitDTO;
import huunl.product.FruitError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateFruitController", urlPatterns = {"/CreateFruitController"})
public class CreateFruitController extends HttpServlet {

    private final static String ERROR = "SearchController";
    private final static String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String categoryID = request.getParameter("categoryID");
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");

            boolean check = true;
            FruitError fruitError = new FruitError();
            if (productID.length() < 5 || productID.length() > 10) {
                fruitError.setProductIDError("productID length must be [5,10]");
                check = false;
            }
            if (productName.length() < 2 || productName.length() > 50) {
                fruitError.setProductNameError("productName length must be [2,50]");
                check = false;
            }
            if (price <= 0) {
                fruitError.setPriceError("price must more than 0!");
            }
            if (quantity <= 0) {
                fruitError.setQuantityError("quantity must more than 0!");
            }
            if (categoryID.length() < 2 || categoryID.length() > 5) {
                fruitError.setCategoryIDError("categoryID length must be [2,5]");
                check = false;
            }
            if (usingDate.compareTo(importDate) < 1) {
                fruitError.setUsingDateError("usingDate must before importDate!");
                check = false;
            }

            if (check) {
                FruitDAO dao = new FruitDAO();
                FruitDTO fruit = new FruitDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate);
                boolean checkDuplicateFruit = dao.checkDuplicateFruit(productID);
                if (checkDuplicateFruit) {
                    fruitError.setProductIDError("Duplicate productID: " + productID + " !");
                    request.setAttribute("FRUIT_ERROR", fruitError);
                } else {
                    boolean checkInsert = dao.insertFruit(fruit);
                    if (checkInsert) {
                        request.setAttribute("INSERT_SUCCESS", "insert successfully!");
                        url = SUCCESS;
                    } else {
                        fruitError.setMessageError("Can not insert, unknown error!");
                        request.setAttribute("FRUIT_ERROR", fruitError);
                    }
                }
            } else {
                request.setAttribute("FRUIT_ERROR", fruitError);
            }

        } catch (Exception e) {
            log("Error at CreateFruitController: " + e.toString());
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
