<%-- 
    Document   : Cart
    Created on : Aug 22, 2023, 8:23:42 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Product"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        h1 {
            text-align: center;
            margin: 20px 0;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        input[type="text"] {
            width: 50px;
            padding: 5px;
        }
        a {
            display: inline-block;
            margin-top: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
<body>
    <h1>Your Shopping Cart</h1>
    <table>
        <tr>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th></th>
                                            
        </tr>
        <%
            double totalCost = 0;
            int totalQuantity = 0;
            java.util.Enumeration em = session.getAttributeNames();            
            while (em.hasMoreElements()) {
                String id = em.nextElement().toString();
                Product pro = (Product) session.getAttribute(id);
                totalCost += pro.getList_price() * pro.getQuantity();
                totalQuantity += pro.getQuantity();
                %>
                
                <tr>
                    <td><%= id %></td>
                    <td><input type="text" id="quantity" value="<%=pro.getQuantity()%>"></td>
                    <td><%=pro.getList_price()%></td>
                    <td><%=pro.getList_price()*pro.getQuantity()%></td>
                    <td><a href="MVCProductController?service=removeProduct?pId=<%=id%>" >Remove</a></td>
                           
                </tr>
                <%
            }
                
                
        %>
        <tr>            
            <td colspan="3"></td>
            <td><%= totalCost %></td>
        </tr>
        <tr>
            <td colspan="1"></td>
            <td><%= totalQuantity %></td>
        </tr>
    </table>
    <p><a href="">Update</a></p>
    <p><a href="Home">Continue Shopping</a></p>
</body>
</html>
