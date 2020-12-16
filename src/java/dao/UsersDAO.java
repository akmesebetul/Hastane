/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Casper
 */

import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDAO extends BaseDao {

    public static boolean validate(String user, String password, String a) { //doğrulamak
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("Select uname,password,userTuru from users where uname = ? and password = ? ");
            ps.setString(1, user);
            ps.setString(2, password);
            ps.setString(3, a);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs //sonuç bulundu geçerli girdiler
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DBConnection.close(con);
        }
        return false;
    }

    static boolean validatePrivileges(String uname, String grup, String lem) { //ayrıcalıkları doğrulama
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("Select uname,grup_name from islemler where uname =? and grup_name =? and " + lem + "=1");

            ps.setString(1, uname);
            ps.setString(2, grup);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DBConnection.close(con);
        }
        return false;
    }

    public static boolean validate(String user, String password) { //doğrulamak
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("Select uname,password from Users where uname = ? and password = ?");
           
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DBConnection.close(con);
        }
        return false;
    }

    public Long users_sayisi(String tablo) {
        Long count = 0L;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from " + tablo);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        count++;
        return count;
    }

    public void insert(String uname, String password) {
       
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("INSERT INTO users(uid,uname,password"
                    + ") VALUES(?,?,?)");
            pst.setLong(1, users_sayisi("users"));
            pst.setString(2, uname);
            pst.setString(3, password);

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public boolean doesExist(String userName) { //var mı
        String userNameExsits = "";
        try {
            String query = "SELECT uname FROM users";
            Statement statement = (Statement) this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString("uname").equals(userName)) {
                    userNameExsits = userName;
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
