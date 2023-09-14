<%-- 
    Document   : Bill
    Created on : Aug 21, 2023, 7:50:57 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="model.DAOBill,java.util.Vector,entity.Bill" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String pageTitle=(String)request.getAttribute("pageTitle");%>
        <title><%=pageTitle%></title>
    </head>
    <body>
         
        <%Vector<Bill> vector =(Vector<Bill>)request.getAttribute("data");%>
        <table border="1">
            <% String pageTable=(String)request.getAttribute("tableTitle");%>
            <caption><%=pageTable%></caption>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Customer</th>                   
                    <th>Total</th>                
                                       
                </tr>
           <%for (Bill bi : vector) {%>
                 <tr>
                    <td><a href="BillController?service=ViewDetail&orderId=<%=bi.getOrder_id()%>"><%=bi.getOrder_id()%></a></td>
                    <td><%=bi.getOrder_date()%></td>                   
                    <td><%=bi.getEmail()%></td>
                    <td/><%=bi.getTotal()%></td>                                     
                                       
                </tr>
            <%}%>
           
        </table>

        
    </body>
</html>
