<%-- 
    Document   : ShowCustomerByJSP
    Created on : Aug 20, 2023, 9:21:08 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="model.DAOCustomer,java.util.Vector,entity.Customer" %>
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
        Vector<Customer> vector =(Vector<Customer>)request.getAttribute("data");
%>
<p><a href="MVCCustomertController?service=insertCustomer">Insert Customer</a></p>
        <table border="1">
            <% String pageTable=(String)request.getAttribute("tableTitle");%>
            <caption><%=pageTable%></caption>
                <tr>
                    <th>Customer ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
           <%for (Customer cus : vector) {%>
                 <tr>
                    <td><%=cus.getCustomer_id()%></td>
                    <td><%=cus.getLast_name()%></td>
                    <td><%=cus.getFirst_name()%></td>
                    <td><%=cus.getPhone()%></td>
                    <td><%=cus.getEmail()%></td>
                    <td><%=cus.getStreet()%></td>
                    <td><%=cus.getCity()%></td>
                    <td><%=cus.getState()%></td>
                    <td><%=cus.getZip_code()%></td>                    
                    <td><a href="MVCCustomerController?service=updateCustomer&pid=<%=cus.getCustomer_id()%>">update</a></td>
                    <td><a href="MVCCustomerController?service=deleteCustomer&pid=<%=cus.getCustomer_id()%>">delete</a></td>
                </tr>
            <%}%>
           
        </table>

        
    </body>
</html>
