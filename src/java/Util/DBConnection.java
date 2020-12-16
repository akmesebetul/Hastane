/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Casper
 */
public class DBConnection {

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void close(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public Connection connect(){
    Connection c = null ;
    try{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
     c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hastane?user=root&password=1905");
    }catch(SQLException e){
            System.out.println(e.getMessage()) ;
    
    }   catch (ClassNotFoundException|InstantiationException|IllegalAccessException ex) {
           
        System.out.println(ex.getMessage())    ; 
    } 
    return c ;
    
    
    }   
}


