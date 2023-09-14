<%-- 
    Document   : ManageBillDetail
    Created on : Aug 23, 2023, 10:26:54 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.Staff"%>
<%@page import="model.DAOBill,java.util.Vector,entity.BillDetail" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styleDashboard.css" rel="stylesheet"/>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.jsp">Manage Page</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <% String fName=(String)request.getAttribute("fname");%>           
            <a class="navbar-brand ps-3"">Welcome Admin : <%=fName%></a>
            
            
            
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <a class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" href="LogoutController?service=logout">Logout</a>
                </div>
            </form>

        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Menu</div>
                            <a class="nav-link" href="MVCProductController">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Manage Products
                            </a>
                            <a class="nav-link" href="MVCCustomerController">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Manage Customers
                            </a>
                            <a class="nav-link" href="MVCStoreController">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Manage Stores
                            </a>
                            <a class="nav-link" href="BillController">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Manage Bills
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Start Bootstrap
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
            <main>
                    <div class="container-fluid px-4">
                        <% String pageTable=(String)request.getAttribute("tableTitle");%>
                        <h1 class="mt-4"><%=pageTable%></h1>                       
                        <div class="card mb-4">
                        </div>
                        <div class="card mb-4">
                            <div class="card-body">  
                                <a  style="float: right; margin-left: 100px" class="btn btn-info" href="add-new-category">Add New</a>                               
                                <% Vector<BillDetail> vector =(Vector<BillDetail>)request.getAttribute("dataRow");%>
                                <% String eName=(String)request.getAttribute("email");%>
                                <table id="datatablesSimple">                                                                    
                                    <thead>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Product ID</th>                   
                                            <th>Product Name</th>
                                            <th>Email</th>
                                            <th>Quantity</th>                   
                                            <th>Price</th>
                                            <th>Discount</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                <%for (BillDetail bd : vector) {%>
                                        <tr>
                                            <td><%=bd.getOrder_id()%></td>
                                            <td><%=bd.getProduct_id()%></td>                    
                                            <td><%=bd.getProduct_name()%></td>
                                            <td><%=eName%></td>
                                            <td><%=bd.getQuantity()%></td>
                                            <td><%=bd.getList_price()%></td>
                                            <td><%=bd.getDiscount()%></td>
                                            <td><%=bd.getQuantity()*bd.getList_price()-bd.getDiscount()%></td>                                            
                                        </tr>
                                <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>    
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
