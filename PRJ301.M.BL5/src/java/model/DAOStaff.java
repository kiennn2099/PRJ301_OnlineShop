/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import entity.Staff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOStaff extends DBConect {
    public boolean login(String userName,String password){
        boolean flag=false;
        String sql="select * from staffs where email=? and phone=?";
        try {
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1,userName);
            pre.setString(2,password);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public Vector getStaffs(String sql) {
        Vector<Staff> vector = new Vector<Staff>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {               
                int id = rs.getInt(1);               
                String fname = rs.getString(2);                
                String lname = rs.getString(3);               
                String email = rs.getString("email");
                String phone = rs.getString(5);                
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);
                Staff st = new Staff(id, fname, lname, email, phone, active,store_id,manager_id);
                vector.add(st);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOStaff.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    public static void main(String[] args) {
        DAOStaff sdao = new DAOStaff();
//        Product pro = new Product(2001, "demo1", 2022, 200, "demo1", "demo1");
//        //int n = dao.insertProduct(pro);
//        int n = dao.updateProduct(pro);
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        //dao.displayAll();
        Vector<Staff> vector = sdao.getStaffs("select * from staffs where email='fabiola.jackson@bikes.shop'");
        for (Staff st : vector) {
            System.out.println("First Name: " + st.getFirst_name());
    //System.out.println("Last Name: " + st.getLast_name());
        }
    }
}
