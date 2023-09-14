<%-- 
    Document   : UpdateStore
    Created on : Aug 24, 2023, 1:24:06 AM
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
        <% 
         Vector<Store> vector =(Vector<Store>)request.getAttribute("dataRow");
         Store st=vector.get(0);
        
        %>
         <form action="StoreController" method="post">
           <input type="hidden" name="service" value="updateStore">
        <table>
            <tr>
                <td><label for="sid">Store ID</label></td>
                <td><input type="text" name="sid" id="sid" value="<%=st.getStore_id()%>" readonly></td>
            </tr>
            <tr>
                <td><label for="sname">Store Name</label></td>
                <td><input type="text" name="sname" id="sname" value="<%=st.getStore_name()%>"></td>
            </tr>
            <tr>
                <td><label for="phone">Phone</label></td>
                <td><input type="text" name="phone" id="phone" value="<%=st.getPhone()%>"></td>
            </tr>
            <tr>
                <td><label for="email">Email</label></td>
                <td><input type="text" name="email" id="email" value="<%=st.getEmail()%>"></td>
            </tr>
            <tr>
                <td><label for="street">Street</label></td>
                <td><input type="text" name="street" id="street" value="<%=st.getStreet()%>"></td>
            </tr>
            <tr>
                <td><label for="city">City</label></td>
                <td><input type="text" name="city" id="city" value="<%=st.getCity()%>"></td>
            </tr>
            <tr>
                <td><label for="state">State</label></td>
                <td><input type="text" name="state" id="state" value="<%=st.getState()%>"></td>                
            </tr>
            <tr>
                <td><label for="zipcode">Zip Code</label></td>
                <td><input type="text" name="zipcode" id="zipcode" value="<%=st.getZip_code()%>"></td>
            </tr>            
            <tr>
                <td><input type="submit" name="submit" value="Update Store"></td>                
            </tr>
        </table>
   </form>
    </body>
</html>
