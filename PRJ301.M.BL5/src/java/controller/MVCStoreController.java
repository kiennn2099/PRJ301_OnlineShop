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
import model.DAOStore;

/**
 *
 * @author admin
 */
public class MVCStoreController extends HttpServlet {

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
        DAOStore dao = new DAOStore();
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "display";
            }
            if (service.equals("display")) {
                // call model
                Vector<Store> vector = dao.getStores("select * from stores");
                // set data for view
                request.setAttribute("data", vector);
                request.setAttribute("pageTitle", "StoreManager");
                request.setAttribute("tableTitle", "StorePList");
                // select view
                RequestDispatcher dis = request.getRequestDispatcher("ManageStore.jsp");
                // run
                dis.forward(request, response);
            }
            if(service.equals("deleteStore")){
                int id=Integer.parseInt(request.getParameter("sid"));
                dao.deleteStore(id);
                
                response.sendRedirect("MVCStoreController");
                
            }
            if(service.equals("updateStore")){
                int id=Integer.parseInt(request.getParameter("sid"));                    
                    Vector<Store> vector = dao.getStores("select * from stores "
                            + " where store_id="+id);
                    // set data for view
                    request.setAttribute("dataRow",vector);                   
                    dispth(request, response, "UpdateStore.jsp");
            }
            if(service.equals("insertStore")){
                    dispth(request, response, "InsertStore.jsp");
                
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
