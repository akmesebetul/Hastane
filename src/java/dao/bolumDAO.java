/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.bolum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import Util.DBConnection1;

/**
 *
 * @author Casper
 */
public class bolumDAO {

    private DBConnection1 db;
    private Connection c;

    public List<bolum> getbolum(int page, int pageSize) {
        List<bolum> blist = new ArrayList();
        // TODO code application logic here
                    int start = (page - 1) * pageSize;

        try {

            PreparedStatement pst = this.getC().prepareStatement("select * from bolum");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //         System.out.println(rs.getString("isim"));
                bolum tmp = new bolum(rs.getLong("id_bolum"), rs.getString("bolum_ismi"));
                blist.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return blist;

    }
    
    public int count() {
        int count = 0;        // TODO code application logic here
        try {

            PreparedStatement pst = this.getC().prepareStatement("select count(id_bolum)as bolum_count from bolum");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("bolum_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;

    }

    public bolum find(Long id_bolum) {
        bolum b = null;
        try {

            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from bolum where id_bolum=" + id_bolum);
            rs.next();
            b = new bolum();
            b.setId_bolum(rs.getLong("id_bolum"));
            b.setBolum_ismi(rs.getString("bolum_ismi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return b;

    }

    public void insert(bolum bolum) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("insert into bolum (bolum_ismi) values (?)");
            pst.setString(1, bolum.getBolum_ismi());
            pst.executeUpdate();

            /* Statement st =this.getC().createStatement();
            st.executeUpdate("insert into bolum (bolum_ismi) values('" + bolum.getBolum_ismi() + "')");
             */
        } catch (SQLException ex) {
        }

    }

    public void delete(bolum bolum) {

        try {

            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from bolum where id_bolum=" + bolum.getId_bolum());

        } catch (SQLException ex) {
        }

    }

    public void update(bolum bolum) {

        try {

            Statement st = this.getC().createStatement();
            st.executeUpdate("update  bolum set bolum_ismi='" + bolum.getBolum_ismi() + "' where id_bolum= " + bolum.getId_bolum());

        } catch (SQLException ex) {
        }

    }

    public DBConnection1 getDb() {
        if (this.db == null) {
            this.db = new DBConnection1();
        }
        return db;
    }

    public Connection getC() {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }

    public void setDb(DBConnection1 db) {
        this.db = db;
    }

    public void setC(Connection c) {
        this.c = c;
    }

}
