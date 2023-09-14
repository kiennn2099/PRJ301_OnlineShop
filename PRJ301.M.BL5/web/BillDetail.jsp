<%-- 
    Document   : BillDetail
    Created on : Aug 21, 2023, 9:24:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="model.DAOBill,java.util.Vector,entity.BillDetail" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String pageTitle=(String)request.getAttribute("pageTitle");%>
        <title><%=pageTitle%></title>
    </head>
    <body>
         
        <%Vector<BillDetail> vector =(Vector<BillDetail>)request.getAttribute("dataRow");%>
        <% String fName=(String)request.getAttribute("email");%>
        <table border="1">
            <% String pageTable=(String)request.getAttribute("tableTitle");%>
            
            <caption><%=pageTable%></caption>
            
                <tr>
                    <th>Order ID</th>
                    <th>Product ID</th>                   
                    <th>Product Name</th>
                    <th>Email</th>
                    <th>Quantity</th>                   
                    <th>Price</th>
                    <th>Discount</th>
                                       
                </tr>
           <%for (BillDetail bd : vector) {%>
                 <tr>
                    <td><%=bd.getOrder_id()%></td>
                    <td><%=bd.getProduct_id()%></td>                    
                    <td><%=bd.getProduct_name()%></td>
                    <td><%=fName%></td>
                    <td><%=bd.getQuantity()%></td>
                    <td><%=bd.getList_price()%></td>
                    <td><%=bd.getDiscount()%></td> 
                                       
                </tr>
            <%}%>
           
        </table>

        
    </body>
</html>
