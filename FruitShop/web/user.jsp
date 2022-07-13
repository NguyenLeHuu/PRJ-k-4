<%-- 
    Document   : user
    Created on : Jan 27, 2022, 11:35:37 AM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="huunl.product.FruitDTO"%>
<%@page import="huunl.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>

    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null || !"US".equals(user.getRoleID().trim())) {
                response.sendRedirect("login.html");
                return;
            }
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Welcome: <%= user.getFullName()%></h1>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
            <input type="submit" name="action" value="View cart"/>
        </form>

        <form action="MainController">
            Search Fruit<input type="text" name="search" value="<%= search%>" placeholder="input to search"/>
            <input type="submit" name="action" value="Search Fruit"/>
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
                    <th>Image</th>
                    <th>productID</th>
                    <th>FruitName</th>
                    <th>price</th>
                    <th>quantity</th>                    
                    <th>Add</th>
                    
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
                        <img src="image/<%= fruit.getImage()%>" alt="Ảnh Trái Cây"/>
                    </td>
                    <td>
                        <input type="text" name="productID" value="<%= fruit.getProductID()%>" readonly=""/>
                    </td>
                    <td>                       
                        <input type="text" name="productName" value="<%= fruit.getProductName()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="number" name="price" value="<%= fruit.getPrice()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="number" name="quantity" value="1"/>
                    </td>
                                    
                    <td>
                        <input type="submit" name="action" value="Add to cart"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                 
                </tr>
            </form>

            <%
                        }
                    }
                }
            %>

        </tbody>
    </table>


    <%
        String message = (String) request.getAttribute("SHOPPING_MESSAGE");
        if (message == null) {
            message = "";
        }
    %>
    <p style="color:white; background-color: green">
        <%= message%>
    </p>

</body>
</html>
