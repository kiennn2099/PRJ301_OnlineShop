<%-- 
    Document   : InsertProduct.jsp
    Created on : Aug 16, 2023, 8:02:13 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
         Vector<Product> vector =(Vector<Product>)request.getAttribute("dataRow");
         Product pro=vector.get(0);
        Vector<String> vectorB =(Vector<String>)request.getAttribute("dataB");
         Vector<String> vectorC =(Vector<String>)request.getAttribute("dataC");
        %>
         <form action="ProductController" method="post">
           <input type="hidden" name="service" value="updateProduct">
        <table>
            <tr>
                <td><label for="pid">ProductID</label></td>
                <td><input type="text" name="pid" id="pid" value="<%=pro.getProduct_id()%>" readonly></td>
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
