/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
import entity.BillDetail;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOBill;


/**
 *
 * @author admin
 */
public class BillController extends HttpServlet {

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
        DAOBill dao = new DAOBill();
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */            
            String service = request.getParameter("service");
            if (service == null) {
                service = "display";
            }
            if (service.equals("display")) {
                // call model
                Vector<Bill> vector = dao.getBills("SELECT o.order_id, o.customer_id, o.order_status, o.order_date, o.required_date, o.shipped_date, o.store_id, o.staff_id, c.email , (s.quantity * s.list_price) as total\n" +
                                                    "FROM orders o \n" +
                                                    "JOIN customers c ON o.customer_id = c.customer_id\n" +
                                                    "JOIN order_items s ON o.order_id = s.order_id");
                // set data for view
                request.setAttribute("data", vector);
                request.setAttribute("pageTitle", "BillManager");
                request.setAttribute("tableTitle", "BillList");
                // select view
                RequestDispatcher dis = request.getRequestDispatcher("ManageBill.jsp");
                // run
                dis.forward(request, response);
            }
            if(service.equals("ViewDetail")){
               String submit = request.getParameter("submit");
                if (submit == null) { // form chua chay--> show jsp
                    //show jsp
                    int id=Integer.parseInt(request.getParameter("orderId"));                   
                    Vector<BillDetail> vector = dao.getBillDetails("SELECT o.order_id, o.item_id, o.product_id, o.quantity, o.list_price, o.discount, c.product_name " +
                                                                   "FROM order_items o " +
                                                                   "JOIN products c ON o.product_id = c.product_id " +
                                                                   "WHERE order_id = " + id);
                    Vector<Bill> vectorA = dao.getBills("SELECT o.order_id, o.customer_id, o.order_status, o.order_date, o.required_date, o.shipped_date, o.store_id, o.staff_id, c.email,"
                            + "(s.quantity * s.list_price) as total "
                            + "FROM orders o "
                            + "JOIN customers c ON o.customer_id = c.customer_id "
                            + "JOIN order_items s ON o.order_id = s.order_id "
                            + "WHERE s.order_id = " + id);
                    for(Bill bi : vectorA){
                        request.setAttribute("email", bi.getEmail());
                    }
                    // set data for view
                    
                    request.setAttribute("dataRow",vector);
                    request.setAttribute("pageTitle", "BillDetailManager");
                    request.setAttribute("tableTitle", "BillDetailList");                    
                    dispth(request, response, "ManageBillDetail.jsp");
                }else{// update                  
                    //code here
                }              
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
