<%-- 
    Document   : ShowStoreByJSP
    Created on : Aug 21, 2023, 11:10:47 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="model.DAOStore,java.util.Vector,entity.Store" %>
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
        Vector<Store> vector =(Vector<Store>)request.getAttribute("data");
%>
<p><a href="MVCStoreController?service=insertStore">Insert Store</a></p>
        <table border="1">
            <% String pageTable=(String)request.getAttribute("tableTitle");%>
            <caption><%=pageTable%></caption>
                <tr>
                    <th>Store ID</th>
                    <th>Store Name</th>
                    <th>Phone</th>                   
                    <th>Email</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
           <%for (Store sto : vector) {%>
                 <tr>
                    <td><%=sto.getStore_id()%></td>
                    <td><%=sto.getStore_name()%></td>                   
                    <td><%=sto.getPhone()%></td>
                    <td><%=sto.getEmail()%></td>
                    <td><%=sto.getStreet()%></td>
                    <td><%=sto.getCity()%></td>
                    <td><%=sto.getState()%></td>
                    <td><%=sto.getZip_code()%></td>                    
                    <td><a href="MVCStoreController?service=updateStore&pid=<%=sto.getStore_id()%>">update</a></td>
                    <td><a href="MVCStoreController?service=deleteStore&pid=<%=sto.getStore_id()%>">delete</a></td>
                </tr>
            <%}%>
           
        </table>

        
    </body>
</html>
