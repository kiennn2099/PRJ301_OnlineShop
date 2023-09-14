<%-- 
    Document   : UpdateCustomer
    Created on : Aug 21, 2023, 7:42:50 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
         Vector<Customer> vector =(Vector<Customer>)request.getAttribute("dataRow");
         Customer cus=vector.get(0);
        Vector<String> vectorB =(Vector<String>)request.getAttribute("dataB");
         Vector<String> vectorC =(Vector<String>)request.getAttribute("dataC");
        %>
         <form action="CustomerController" method="post">
           <input type="hidden" name="service" value="updateCustomer">
        <table>
            <tr>
                <td><label for="cid">CustomerID</label></td>
                <td><input type="text" name="cid" id="cid" value="<%=cus.getCustomer_id()%>" readonly></td>
            </tr>
            <tr>
                <td><label for="pname">Product Name</label></td>
                <td><input type="text" name="pname" id="pname" value="<%=pro.getProduct_name()%>"></td>
            </tr>
            <tr>
                <td><label for="model">Model year</label></td>
                <td><input type="text" name="model" id="model" value="<%=pro.getModel_year()%>"></td>
            </tr>
            <tr>
                <td><label for="price">Price</label></td>
                <td><input type="number" name="price" id="price" value="<%=pro.getList_price()%>"></td>
            </tr>
            <tr>
                <td><label for="brand">Brand Name</label></td>
                <td><select name="brand" id="brand">
                   
                        <%for (String temp : vectorB) {%>
                      
                        <option value="<%=temp%>"><%=temp%></option>
                    <%}%>    
                       
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="Category">Category</label></td>
                <td><select name="Category" id="Category">
                        
                      <%for (String temp : vectorC) {%>
                      
                        <option value="<%=temp%>"><%=temp%></option>
                    <%}%>    
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="Update Product"></td>
                <td><input type="reset" value="reset"></td>
            </tr>
        </table>
   </form>
    </body>
</html>
