<%-- 
    Document   : createUser
    Created on : Jan 29, 2022, 9:56:02 AM
    Author     : Admin
--%>

<%@page import="huunl.users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register form!</h1>
        <%
            UserError userError = (UserError)request.getAttribute("USER_ERROR");
            if(userError == null){
                userError = new UserError();
            }
        %>
        <form action="MainController" method="POST">
            User ID<input type="text" name="userID" required=""/><%= userError.getUserIDError() %></br>
            full Name<input type="text" name="fullName" required=""/><%= userError.getFullNameError()%></br>
            role ID<input type="text" name="roleID" value="US" readonly=""/><%= userError.getRoleIDError()%></br>
            password<input type="password" name="password" required=""><%= userError.getPasswordError()%></br>
            confirm<input type="password" name="confirm" required=""/><%= userError.getConfirmError()%></br>
            address<input type="text" name="address"/><%= userError.getAddressError()%></br>
            birthday<input type="date" name="birthday"/><%= userError.getBirthdayError()%></br>
            phone<input type="number" name="phone" required=""/><%= userError.getPhoneError()%></br>
            gmail<input type="email" name="gmail" required=""/><%= userError.getGmailError()%></br>
            
            <input type="submit" name="action" value="Register"/>
            <input type="reset" value="Reset"/>
            <%= userError.getMessageError()%>
        </form>
    </body>
</html>
