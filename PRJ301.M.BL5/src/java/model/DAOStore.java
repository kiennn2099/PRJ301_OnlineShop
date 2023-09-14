/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import entity.Staff;
import entity.Store;
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
public class DAOStore extends DBConect{
    public Vector getStores(String sql) {
        Vector<Store> vector = new Vector<Store>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {               
                int id = rs.getInt(1);               
                String name = rs.getString(2);                
                String phone = rs.getString(3);               
                String email = rs.getString("email");
                String street = rs.getString(5);                
                String city = rs.getString(6);
                String state_ = rs.getString(7);
                String zip_code = rs.getString(8);
                Store sto = new Store(id, name, phone, email, street, city,state_,zip_code);
                vector.add(sto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    public int deleteStore(int id) {
        int n = 0;
        String sql = "delete from stores where store_id =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int updateStore(Store st) {
        int n = 0;
        String sql = "UPDATE [dbo].[stores]\n"              
                + "   SET [store_name] = ?"
                + "      ,[phone] = ?"
                + "      ,[email] = ?"
                + "      ,[street] = ?"
                + "      ,[city] = ?"
                + "      ,[state] = ?"
                + "      ,[zip_code] = ?"
                + " WHERE [store_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, st.getStore_name());
            pre.setString(2, st.getPhone());
            pre.setString(3, st.getEmail());
            pre.setString(4, st.getStreet());
            pre.setString(5, st.getCity());
            pre.setString(6, st.getState());
            pre.setString(7, st.getZip_code());
            pre.setInt(8, st.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    public int insertStore(Store st) {
    int n = 0;
    String sql = "INSERT INTO [dbo].[stores] "
            + "([store_id], [store_name], [phone], [email], [street], [city], [state], [zip_code]) "
            + "VALUES(" + st.getStore_id() + ",'" + st.getStore_name()
            + "','" + st.getPhone() + "','" + st.getEmail()
            + "','" + st.getStreet() + "','" + st.getCity()
            + "','" + st.getState() + "','" + st.getZip_code()+ "')";

    try {
        Statement state = conn.createStatement();
        n = state.executeUpdate(sql);
    } catch (SQLException ex) {
        Logger.getLogger(DAOStore.class.getName()).log(Level.SEVERE, null, ex);
    }

    return n;
}

    public static void main(String[] args) {
        DAOStore dao = new DAOStore();
//        Product pro = new Product(2001, "demo1", 2022, 200, "demo1", "demo1");
//        //int n = dao.insertProduct(pro);
//        int n = dao.updateProduct(pro);
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        //dao.displayAll();
        Vector<Store> vector = dao.getStores("select * from stores");
        for (Store st : vector) {
            System.out.println(st);
    //System.out.println("Last Name: " + st.getLast_name());
        }
    }
    
}
