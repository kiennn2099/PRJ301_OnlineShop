 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
public class DAOProduct extends DBConect { // DAO: database accesss object

    public int insertProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[model_year]\n"
                + "           ,[list_price]\n"
                + "           ,[brand_name]\n"
                + "           ,[category_name])\n"
                + "     VALUES(" + pro.getProduct_id() + ",'" + pro.getProduct_name()
                + "'," + pro.getModel_year() + "," + pro.getList_price()
                + ",'" + pro.getBrand_name() + "','" + pro.getCategory_name() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int insertProductByPre(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[model_year]\n"
                + "           ,[list_price]\n"
                + "           ,[brand_name]\n"
                + "           ,[category_name])\n"
                + "     VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
//                    pre.setDataType(indexOf?,value);
//                    DataType is dataType of fieldName
//                    indexOf?: index start 1 ]
            pre.setInt(1, pro.getProduct_id());
            pre.setString(2, pro.getProduct_name());
            pre.setInt(3, pro.getModel_year());
            pre.setDouble(4, pro.getList_price());
            pre.setString(5, pro.getBrand_name());
            pre.setString(6, pro.getCategory_name());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [product_name] = ?"
                + "      ,[model_year] = ?"
                + "      ,[list_price] = ?"
                + "      ,[brand_name] = ?"
                + "      ,[category_name] = ?"
                + " WHERE [product_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProduct_name());
            pre.setInt(2, pro.getModel_year());
            pre.setDouble(3, pro.getList_price());
            pre.setString(4, pro.getBrand_name());
            pre.setString(5, pro.getCategory_name());
            pre.setInt(6, pro.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int changePrice(int id, double newPrice) {
        int n = 0;
        String sql = "UPDATE [dbo].[products]  SET [list_price] =" + newPrice
                + " WHERE [product_id] =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteProduct(int id) {
        int n = 0;
        String sql = "delete from products where [product_id] =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector searchByName(String name) {
        Vector<Product> vector = new Vector<Product>();
        String sql = "select * from products where product_name Like'%" + name + "%'";
        ResultSet rs=getData(sql);
        try {
            while(rs.next()){
                int id = rs.getInt("product_id");
                // int id=rs.getInt(1);
                //String name = rs.getString(2);
                //   String name=rs.getString("product_name");
                int year = rs.getInt(3);
                double price = rs.getDouble(4);
                String brand = rs.getString(5);
                String cate = rs.getString(6);
                Product pro = new Product(id, name, year, price, brand, cate);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    public Vector getField(String name){
        Vector<String> vector=new Vector<String>();
        ResultSet rs=this.getData("select distinct "+name+" from Products");
        try {
            while(rs.next()){
                vector.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public Product getProductById(int pid){
       String sql = "select * from products where Product_id= '"+pid+"'";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getDataType(fieldName|index);
                int id = rs.getInt(1);
                // int id=rs.getInt(1);
                String name = rs.getString(2);
                //   String name=rs.getString("product_name");
                int year = rs.getInt(3);
                double price = rs.getDouble(4);
                String brand = rs.getString(5);
                String cate = rs.getString(6);
                Product pro = new Product(id, name, year, price, brand, cate);
                System.out.println(pro);
                return pro;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    

    public Vector getProducts(String sql) {
        Vector<Product> vector = new Vector<Product>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getDataType(fieldName|index);
                int id = rs.getInt("product_id");
                // int id=rs.getInt(1);
                String name = rs.getString(2);
                //   String name=rs.getString("product_name");
                int year = rs.getInt(3);
                double price = rs.getDouble(4);
                String brand = rs.getString(5);
                String cate = rs.getString(6);
                Product pro = new Product(id, name, year, price, brand, cate);
                vector.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public void displayAll() {
        String sql = "select * from Products";
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName=rs.getDataType(fieldName|index);
                int id = rs.getInt("product_id");
                // int id=rs.getInt(1);
                String name = rs.getString(2);
                //   String name=rs.getString("product_name");
                int year = rs.getInt(3);
                double price = rs.getDouble(4);
                String brand = rs.getString(5);
                String cate = rs.getString(6);
                Product pro = new Product(id, name, year, price, brand, cate);
                System.out.println(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
//        Product pro = new Product(2001, "demo1", 2022, 200, "demo1", "demo1");
//        //int n = dao.insertProduct(pro);
//        int n = dao.updateProduct(pro);
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        //dao.displayAll();
        
        Product pro = dao.getProductById(1);
        
            System.out.println(pro);
        
    }
}
