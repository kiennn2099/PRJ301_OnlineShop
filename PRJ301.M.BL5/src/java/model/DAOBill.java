/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Bill;
import entity.BillDetail;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOBill extends DBConect{
    public Vector getBills(String sql) {
        Vector<Bill> vector = new Vector<Bill>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                int status = rs.getInt(3);
                Date orderDate = rs.getDate(4);
                Date requiredDate = rs.getDate(5);
                Date shippedDate = rs.getDate(6);
                int storeId = rs.getInt(7);
                int staffId = rs.getInt(8);
                String email = rs.getString(9);
                double total = rs.getDouble("total");
                
                
                Bill bi = new Bill(orderId , customerId, status, orderDate , requiredDate, shippedDate, storeId, staffId, email,total );
                vector.add(bi);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    public Vector getBillDetails(String sql) {
        Vector<BillDetail> vector = new Vector<BillDetail>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                
                int orderId = rs.getInt(1);
                int itemId = rs.getInt(2);
                int productId = rs.getInt(3);
                int quantity = rs.getInt(4);               
                double price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                String ProductName = rs.getString(7);
                
                
                BillDetail bd = new BillDetail(orderId , itemId, productId, quantity , price, discount, ProductName);
                vector.add(bd);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    public static void main(String[] args) {
        DAOBill dao = new DAOBill();
//        Product pro = new Product(2001, "demo1", 2022, 200, "demo1", "demo1");
//        //int n = dao.insertProduct(pro);
//        int n = dao.updateProduct(pro);
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        //dao.displayAll();
        int id = 1;
        Vector<Bill> vectorA = dao.getBills("SELECT o.order_id, o.customer_id, o.order_status, o.order_date, o.required_date, o.shipped_date, o.store_id, o.staff_id, c.email,(s.quantity * s.list_price) as total FROM orders o JOIN customers c ON o.customer_id = c.customer_id JOIN order_items s ON o.order_id = s.order_id WHERE s.order_id = " + id);
                    
        for (Bill st : vectorA) {
            System.out.println(st.getEmail());
    //System.out.println("Last Name: " + st.getLast_name());
        }
    }
}
