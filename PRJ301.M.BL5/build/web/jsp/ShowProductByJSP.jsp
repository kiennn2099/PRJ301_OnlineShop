<%-- 
    Document   : ShowProductByJSP
    Created on : Aug 15, 2023, 8:43:15 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="model.DAOProduct,java.util.Vector,entity.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String pageTitle=(String)request.getAttribute("pageTitle");%>
        <title><%=pageTitle%></title>
    </head>
    <body>
        <% //DAOProduct dao=new DAOProduct();
       // Vector<Product> vector = dao.getProducts("select * from products");
        Vector<Product> vector =(Vector<Product>)request.getAttribute("data");
%>
<p><a href="MVCProductController?service=insertProduct">Insert Product</a></p>
        <table border="1">
            <% String pageTable=(String)request.getAttribute("tableTitle");%>
            <caption><%=pageTable%></caption>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Model year</th>
                    <th>Price</th>
                    <th>Brand Name</th>
                    <th>Category</th>
                    <th>update</th>
                    <th>delete</th>
                    
                </tr>
           <%for (Product pro : vector) {%>
                 <tr>
                    <td><%=pro.getProduct_id()%></td>
                    <td><%=pro.getProduct_name()%></td>
                    <td><%=pro.getModel_year()%></td>
                    <td><%=pro.getList_price()%></td>
                    <td><%=pro.getBrand_name()%></td>
                    <td><%=pro.getCategory_name()%></td>
                    <td><a href="MVCProductController?service=updateProduct&pid=<%=pro.getProduct_id()%>">update</a></td>
                    <td><a href="MVCProductController?service=deleteProduct&pid=<%=pro.getProduct_id()%>">delete</a></td>
                    
                </tr>
            <%}%>
           
        </table>

        
    </body>
</html>
