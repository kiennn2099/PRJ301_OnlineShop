<%-- 
    Document   : InsertStore
    Created on : Aug 24, 2023, 2:01:17 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Store" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
         <form action="StoreController" method="post">
           <input type="hidden" name="service" value="insertStore">
        <table>
            <tr>
                <td><label for="sid">Store ID</label></td>
                <td><input type="text" name="sid" id="sid" ></td>
            </tr>
            <tr>
                <td><label for="sname">Store Name</label></td>
                <td><input type="text" name="sname" id="sname" ></td>
            </tr>
            <tr>
                <td><label for="phone">Phone</label></td>
                <td><input type="text" name="phone" id="phone" ></td>
            </tr>
            <tr>
                <td><label for="email">Email</label></td>
                <td><input type="text" name="email" id="email" ></td>
            </tr>
            <tr>
                <td><label for="street">Street</label></td>
                <td><input type="text" name="street" id="street" ></td>
            </tr>
            <tr>
                <td><label for="city">City</label></td>
                <td><input type="text" name="city" id="city" ></td>
            </tr>
            <tr>
                <td><label for="state">State</label></td>
                <td><input type="text" name="state" id="state" ></td>                
            </tr>
            <tr>
                <td><label for="zipcode">Zip Code</label></td>
                <td><input type="text" name="zipcode" id="zipcode" ></td>
            </tr>            
            <tr>
                <td><input type="submit" name="submit" value="Insert Store"></td>                
            </tr>
        </table>
   </form>
    </body>
</html>
