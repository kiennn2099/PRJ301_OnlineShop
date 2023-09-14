<%-- 
    Document   : Demo
    Created on : Aug 15, 2023, 8:13:27 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           // ResultSet rs;
        %>
        <!--scrip-->
        <%
            int a=100;
            out.print("a="+a);
         %>
         <!--expression-->
         <h1 style="color:red">value a= <%=a%> </h1>
        
         <% for(int i=10; i<=a;i+=10){
         %>
            <hr width="<%=i%>">
         <%}%>
         <!--declared-->
         <%int MAX=1000;%>
         <%! int MIN=0;%>
         <%!
             public String getName(String name){
                return "hello "+name;
            }
         %>
         <H2><%=getName("John")%></H2>
    </body>
</html>
