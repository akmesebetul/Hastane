/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.user;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Casper
 */
public class LoginDao extends BaseDao implements Serializable {

    public user validate(String user) {
        Connection con;

        PreparedStatement ps;

        user user2 = null;

        try {
            con = this.getConnection();
            ps = con.prepareStatement("select * from user  where  uname='" + user + "'");

            ResultSet rs = ps.executeQuery();
            rs.next();
            user2 = new user();

            user2.setUserName(rs.getString("uname"));
            user2.setPassword(rs.getString("password"));

        } catch (SQLException ex) {
            return null;

        }
        return user2;
    }

}
