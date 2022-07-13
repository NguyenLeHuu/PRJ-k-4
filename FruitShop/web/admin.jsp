<%-- 
    Document   : admin
    Created on : Jan 27, 2022, 11:30:33 AM
    Author     : Admin
--%>

<%@page import="huunl.product.FruitError"%>
<%@page import="huunl.product.FruitDTO"%>
<%@page import="java.util.List"%>
<%@page import="huunl.users.UserDTO"%>


<!DOCTYPE html>
<html>
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID().trim())) {
                response.sendRedirect("login.html");
                return;
            }
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }

        %>
        <h1>Welcome: <%= loginUser.getFullName()%></h1>

        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>

        <form action="MainController">
            Search Fruit<input type="text" name="search" value="<%= search%>" placeholder="input to search"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <%
            List<FruitDTO> list = (List<FruitDTO>) request.getAttribute("LIST_FRUIT");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>image</th>
                    <th>productID</th>                   
                    <th>FruitName</th>
                    <th>price</th>
                    <th>quantity</th>
                    <th>categoryID</th>
                    <th>importDate</th>
                    <th>usingDate</th>                    
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (FruitDTO fruit : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>                  
                    <td>
                        <img style="width: 100px;"src="image/<%= fruit.getImage()%>" alt="Ảnh Trái Cây"/>
                    </td>
                    <td>
                        <%= fruit.getProductID()%>
                    </td>
                    <td>                       
                        <input type="text" name="productName" value="<%= fruit.getProductName()%>"/>
                    </td>
                    <td>
                        <input type="number" name="price" value="<%= fruit.getPrice()%>"/>
                    </td>
                    <td>
                        <input type="number" name="quantity" value="<%= fruit.getQuantity()%>"/>
                    </td>
                    <td>
                        <input type="text" name="categoryID" value="<%= fruit.getCategoryID()%>"/>
                    </td>
                    <td>
                        <input type="date" name="importDate" value="<%= fruit.getImportDate()%>"/>
                    </td>
                    <td>
                        <input type="date" name="usingDate" value="<%= fruit.getUsingDate()%>"/>
                    </td>                   
                    <td>
                        <a href="MainController?action=Delete Fruit&productID=<%= fruit.getProductID()%>&search=<%= search%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update Fruit"/> 
                        <input type="hidden" name="productID" value="<%= fruit.getProductID()%>"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                </tr>
            </form>

            <%
                }
            %>

        </tbody>
    </table>




    <%
        }
        String error_message = (String) request.getAttribute("ERROR_MESSAGE");
        if (error_message == null) {
            error_message = "";
        }
    %>

    <h1><%= error_message%></h1>
    <%
        }
    %>


    <h2>Create new Fruit</h2> 
    <%
        FruitError fruitError = (FruitError) request.getAttribute("FRUIT_ERROR");
        if (fruitError == null) {
            fruitError = new FruitError();
        }
        String insert_success = (String) request.getAttribute("INSERT_SUCCESS");
        if (insert_success == null) {
            insert_success = "";
        }
    %>
    <form action="MainController" method="POST">
        productID<input type="text" name="productID" required=""/><span style="color: orange"><%= fruitError.getProductIDError()%></span></br>
        productName<input type="text" name="productName" required=""/><span style="color: orange"><%= fruitError.getProductNameError()%></span></br>
        image<input type="text" name="image" /></br>
        price<input type="number" name="price" required=""/><span style="color: orange"><%= fruitError.getPriceError()%></span></br>
        quantity<input type="number" name="quantity" required=""><span style="color: orange"><%= fruitError.getQuantityError()%></span></br>
        categoryID<input type="text" name="categoryID" required=""/><span style="color: orange"><%= fruitError.getCategoryIDError()%></span></br>
        importDate<input type="date" name="importDate" required=""/><span style="color: orange"><%= fruitError.getImportDateError()%></span></br>
        usingDate<input type="date" name="usingDate" required=""/><span style="color: orange"><%= fruitError.getUsingDateError()%></span></br>

        <input type="submit" name="action" value="Add Fruit"/>
        <input type="reset" value="Reset"/>
        <h3 style="color: green"><%= insert_success%></h3>
        <h3 style="color: orange"><%= fruitError.getMessageError()%></h3>
    </form>

</body>
</html>

