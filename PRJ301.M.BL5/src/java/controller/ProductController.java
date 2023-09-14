/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOProduct dao = new DAOProduct();
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "display";
            }
            if (service.equals("display")) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductController</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>  <a href=\"insertProduct.html\">insert Product </a></h1>");
                // out.println("<h1> Display something</h1>");
                out.println(" <table border=\"1\">\n"
                        + "            <caption>List of Products</caption>\n"
                        + "                <tr>\n"
                        + "                    <th>Product ID</th>\n"
                        + "                    <th>Product Name</th>\n"
                        + "                    <th>Model year</th>\n"
                        + "                    <th>Price</th>\n"
                        + "                    <th>Brand Name</th>\n"
                        + "                    <th>Category</th>\n"
                        + "                    <th>update</th>\n"
                        + "                    <th>delete</th>\n"
                        + "                </tr>");
                Vector<Product> vector = dao.getProducts("select * from products");
                for (Product pro : vector) {
                    out.println(" <tr>\n"
                            + "                    <td>"+pro.getProduct_id()+"</td>\n"
                            + "                    <td>"+pro.getProduct_name()+"</td>\n"
                            + "                    <td>"+pro.getModel_year()+"</td>\n"
                            + "                    <td>"+pro.getList_price()+"</td>\n"
                            + "                    <td>"+pro.getBrand_name()+"</td>\n"
                            + "                    <td>"+pro.getCategory_name()+"</td>\n"
                            + "                    <td> <a href=\"ProductController?service=updateProduct&pid="+pro.getProduct_id()+"\">update</a></td>\n"
                            + "                    <td><a href=\"ProductController?service=deleteProduct&pid="+pro.getProduct_id()+"\">delete</a></td>\n"
                            + "                </tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
            if(service.equals("deleteProduct")){
                int id=Integer.parseInt(request.getParameter("pid"));
                dao.deleteProduct(id);
                response.sendRedirect("ProductController");
                
            }
            //Get data from client: normal data : String
            if (service.equals("insertProduct")) {
                String id = request.getParameter("pid");
                String name = request.getParameter("pname");
                String model = request.getParameter("model");
                String price = request.getParameter("price");
                String brand = request.getParameter("brand");
                String Category = request.getParameter("Category");
                // check value
                // check null
                int modelInt = 0;
                if (model == null || model.equals("")) {
                    out.print("pls input model year");
                } else {
                    modelInt = Integer.parseInt(model);
                }
                int pid = Integer.parseInt(id);
                double dprice = Double.parseDouble(price);
                //insert
                Product pro = new Product(pid, name, modelInt, dprice,
                        brand, Category);
                int n = dao.insertProductByPre(pro);
//                if (n > 0) {
//                    out.print("inserted");
//                }
                  response.sendRedirect("ProductController");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
