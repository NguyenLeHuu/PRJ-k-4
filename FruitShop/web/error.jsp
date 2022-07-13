<%-- 
    Document   : error
    Created on : Jan 27, 2022, 11:14:34 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h3 style="color: red">Error: <%= session.getAttribute("ERROR_MESSAGE") %></h3>
        <button style="background-color: azure; "><a href="login.html">Login again</a></button>
    </body>
</html>
