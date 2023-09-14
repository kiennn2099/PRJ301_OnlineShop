/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import entity.Store;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;
import model.DAOStore;

/**
 *
 * @author admin
 */
public class FilterController extends HttpServlet {

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
        DAOStore Sdao = new DAOStore();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");           
            
            if (service.equals("filter")) {
               int id = Integer.parseInt(request.getParameter("sid"));
               Vector<Product> vector = dao.getProducts("SELECT p.product_id, p.product_name, p.model_year, p.list_price, p.brand_name, p.category_name\n" +
                                                        "FROM products p\n" +
                                                        "INNER JOIN stocks s ON p.product_id = s.product_id\n" +
                                                        "INNER JOIN stores st ON s.store_id = st.store_id\n" +
                                                        "WHERE st.store_id = "+ id);
               Vector<Store> vectorA = Sdao.getStores("select * from stores");
               request.setAttribute("store", vectorA);
                // set data for view
                request.setAttribute("product", vector);
                // run
               dispth(request, response, "Home.jsp"); 
            }
            if (service.equals("search")) {
                String txt = request.getParameter("txtSearch");
                Vector<Product> vector = dao.getProducts("select * from products where product_name LIKE '%"+txt+"%'");
                Vector<Store> vectorA = Sdao.getStores("select * from stores");
                request.setAttribute("store", vectorA);
                // set data for view
                request.setAttribute("product", vector);
                // run
               dispth(request, response, "Home.jsp");
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
