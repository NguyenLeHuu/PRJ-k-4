<%-- 
    Document   : viewCart
    Created on : Feb 28, 2022, 11:21:13 PM
    Author     : Admin
--%>

<%@page import="huunl.shopping.Fruit"%>
<%@page import="huunl.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>viewCart Page</title>
    </head>
    <body>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null || cart.getCart().size() == 0) {
                String order_success = (String) request.getAttribute("ORDER_SUCCESS");
                if (order_success == null) {
                    order_success = "";
                }
        %>
        <h3 style="color: green"><%= order_success %></h3>
        <h1 style="color: orange">khong co san pham nao</h1><button style="background-color: azure"><a href="user.jsp?">Add More</a></button>
        <%
        } else {
            if (cart.getCart().size() > 0) {


        %>
        <%             String error_message = (String) request.getAttribute("ERROR_MESSAGE");
            if (error_message == null) {
                error_message = "";
            }
        %>


        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Update</th>
                    <th>Remove</th>

                </tr>
            </thead>
            <tbody>
                <%  int count = 1;
                    double total = 0;
                    for (Fruit fruit : cart.getCart().values()) {
                        total += fruit.getQuantity() * fruit.getPrice();
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <input type="text" name="id" value="<%= fruit.getId()%>" readonly=""/>
                    </td>
                    <td><%= fruit.getName()%></td>
                    <td>
                        <input type="number" name="quantity" value="<%= fruit.getQuantity()%>" required=""/>
                    </td>
                    <td><%= fruit.getPrice()%></td>
                    <td><%= fruit.getQuantity() * fruit.getPrice()%></td>
                    <td>
                        <input type="submit" name="action" value="Modify"/>                      
                    </td>
                    <td>
                        <input type="submit" name="action" value="Remove"/>                      
                    </td>



                </tr>
            </form>

            <%
                }
            %>

        </tbody>
    </table>
    <button style="background-color: azure">
        <a href="MainController?action=Check Out">Check Out</a>
    </button>
    <span style="color:orange"><%= error_message%></span>


    <h1 style="color:green">Total <%= total%>k</h1>

    <button style="background-color: azure"><a href="user.jsp?">Add More</a></button>
    


    <%
            }
        }
    %>
</body>
</html>
