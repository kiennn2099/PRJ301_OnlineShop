<%-- 
    Document   : Home
    Created on : Aug 22, 2023, 7:52:46 AM
    Author     : admin
--%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAOStore,entity.Store" %>
<%@page import="model.DAOProduct,entity.Product" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Home</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>

    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-lg-5">
                <a class="navbar-brand" >Home</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <form action="search" method="POST" class="d-flex w-50">
                        <input type="hidden" value="${cid}" name="cid" />                       
                        <input name="txt" class="form-control me-2" type="search" placeholder="" value="${txtSearch}"
                               aria-label="Search">
                        <button class="btn btn-success" type="submit">Search</button>
                    </form>
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <% String fName=(String)request.getAttribute("fname");%>
                        <li class="nav-item"><a class="nav-link active" aria-current="page">WelCome: <%=fName%></a></li>
                        <li class="nav-item"><a class="nav-link" href="LoginCus">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="LogoutController?service=logoutCus">Logout</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Register</a></li>
                        <li class="nav-item"><a class="nav-link" href="jsp/Cart.jsp">Show Cart</a></li>
                            
                    </ul>
                </div>
            </div>
        </nav>

        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row">
                    <div class="col-md-4">
                        <form action="filter-category">      
                            <h3>List Stores</h3> 
                            <select onchange="this.form.submit()" name="cid">
                                <option value="-1">All Stores</option>
                                <% Vector<Store> vectorA =(Vector<Store>)request.getAttribute("store");%>
                                <%for (Store st : vectorA) {%>
                                    <option <%=st.getStore_id()%>"selected":""} value=""><%=st.getStore_name()%></option>
                                <%}%>
                            </select>
                           
                        </form>
                        <br>                           
                    </div>

                    <div class="col-md-8">
                        <h3>List Products</h3>
                        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                            <% Vector<Product> vector =(Vector<Product>)request.getAttribute("product");%>
                            <%for (Product pro : vector) {%> 
                                <div class="col mb-5">                                    
                                    <div class="card h-100">
                                        <!-- Product image-->
                                        <img class="card-img-top" src="https://png.pngtree.com/png-vector/20191003/ourlarge/pngtree-bike-icon-outline-style-png-image_1786325.jpg"
                                             alt="..." />
                                        <!-- Product details-->
                                        <div class="card-body p-4">
                                            <div class="text-center">
                                                <!-- Product name-->
                                                <h5 class="fw-bolder"><%=pro.getProduct_name()%></h5>
                                                
                                                <!-- Product price-->
                                                <h6><%=pro.getList_price()%>$</h6>
                                                <p><%=pro.getBrand_name()%></p>
                                                <p><%=pro.getModel_year()%></p>

                                                
                                            </div>
                                        </div>
                                        <!-- Product actions-->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="MVCProductController?service=add2cart&pid=<%=pro.getProduct_id()%>">Add to cart</a></div>
                                        </div>
                                    </div>
                                </div>
                            <%}%>

                        </div>
                        </form>
                          
                    </div>
                   
                </div>

            </div>
        </section>
        <!-- Footer-->
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>

</html>
