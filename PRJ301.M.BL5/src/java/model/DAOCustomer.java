/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DAOCustomer extends DBConect {
    public boolean login(String userName,String password){
        boolean flag=false;
        String sql="select * from Customers where email=? and phone=?";
        try {
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1,userName);
            pre.setString(2,password);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public Vector getCustomers(String sql) {
        Vector<Customer> vector = new Vector<Customer>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getDataType(fieldName|index);
                int id = rs.getInt("customer_id");
                // int id=rs.getInt(1);
                String fname = rs.getString(2);
                //   String name=rs.getString("product_name");
                String lname = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state_ = rs.getString(8);
                String zipcode = rs.getString(9);       
                
                Customer cus = new Customer(id, fname, lname, phone, email, street, city, state_, zipcode);
                vector.add(cus);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        String name ="latasha.hays@hotmail.com";
        String pass = "(716) 986-3359";
        if(dao.login(name,pass)==true){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        
    }

    public Vector<Product> getStaffs(String select__from_Products) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
