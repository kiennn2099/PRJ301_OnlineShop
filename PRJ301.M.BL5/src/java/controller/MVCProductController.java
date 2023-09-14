/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOProduct;
import model.DBConect;

/**
 *
 * @author DELL
 */
public class MVCProductController extends HttpServlet {

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
        HttpSession session=request.getSession(true);
        DBConect db=new DBConect();
        DAOProduct dao = new DAOProduct();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "display";
            }
            if (service.equals("display")) {
                // call model
                Vector<Product> vector = dao.getProducts("select * from Products");
                // set data for view
                request.setAttribute("data", vector);
                request.setAttribute("pageTitle", "ProductManager");
                request.setAttribute("tableTitle", "ProductList");
                // select view
                RequestDispatcher dis = request.getRequestDispatcher("Menu.jsp");
                // run
                dis.forward(request, response);
            }
            if (service.equals("deleteProduct")) {
                int id = Integer.parseInt(request.getParameter("pid"));
                dao.deleteProduct(id);
                response.sendRedirect("MVCProductController");

            }
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // form chua chay--> show jsp
                    //show jsp
                    Vector<Product> vectorB = dao.getField("brand_name");
                    Vector<Product> vectorC = dao.getField("category_name");
                    // set data for view
                    request.setAttribute("dataB", vectorB);
                    request.setAttribute("dataC", vectorC);
                    dispth(request, response, "InsertProduct.jsp");
                }else{// insert
                    //code here
                }
            }
            if(service.equals("updateProduct")){
               String submit = request.getParameter("submit");
                if (submit == null) { // form chua chay--> show jsp
                    //show jsp
                    int id=Integer.parseInt(request.getParameter("pid"));
                    Vector<Product> vectorB = dao.getField("brand_name");
                    Vector<Product> vectorC = dao.getField("category_name");
                    Vector<Product> vector = dao.getProducts("select * from Products "
                            + " where product_id="+id);
                    // set data for view
                    request.setAttribute("dataRow",vector);
                    request.setAttribute("dataB", vectorB);
                    request.setAttribute("dataC", vectorC);
                    dispth(request, response, "UpdateProduct.jsp");
                }else{// update
                    //code here
                }
            }
            if(service.equals("add2cart")){
                int id=Integer.parseInt(request.getParameter("pid"));
                // select Product by ID --> Pro
                
                Product pro=(Product)session.getAttribute(id+"");
                if(pro==null){
                    Product proDB = dao.getProductById(id);
                    proDB.setQuantity(1);
                    session.setAttribute(id+"",proDB);
                     //Product proDB=db.getData("");
                }else{
                    //pro session
                    pro.setQuantity(pro.getQuantity()+1);
                    session.setAttribute(id+"", pro);
                }
                //check product exist ? --> set proDB quantity=1 (pro from DB)
                // or set pro quantity +1: proSession
                response.sendRedirect("Cart.jsp");
            }
            if(service.equals("removeProduct")){
                int id=  Integer.parseInt(request.getParameter("pId"));
               
                session.removeAttribute(id+"");               
                response.sendRedirect("Cart.jsp");
            }
        }
    }


    public void dispth(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws IOException, ServletException {
        RequestDispatcher dis = request.getRequestDispatcher(page);
        // run
        dis.forward(request, response);
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
