/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DBConect {
    Connection conn=null;
    
    public DBConect(String URL,String username,String pass){
         try {
            //driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // connection
            conn=DriverManager.getConnection(URL,username,pass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public DBConect(){
this("jdbc:sqlserver://localhost:1433;databaseName=demoPRJ301","sa","123456");
//        try {
//            //driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            // connection
            //conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=demoPRJ301","sa","123456");
//            System.out.println("Connected");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
       
    }
    public ResultSet getData(String sql){
        ResultSet rs=null;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
           rs = state.executeQuery(sql);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rs;
    }
    public static void main(String[] args) {
        new DBConect();
    }
}
