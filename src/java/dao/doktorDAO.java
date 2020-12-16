/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.doktor;
import entity.hasta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.DBConnection1;

/**
 *
 * @author Casper
 */
public class doktorDAO {
    
    private DBConnection1 db;
    private Connection c;
    private hastaDAO hastaDAO;

    public List<doktor> getdoktor(int page, int pageSize) {
        List<doktor> dlist = new ArrayList<>();
       
        int start = (page - 1) * pageSize; //sayfalandırma

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from doktor order by id_dok asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               
                doktor tmp = new doktor();
                tmp.setId_dok(rs.getLong("id_dok"));
                tmp.setIsim(rs.getString("isim"));
                tmp.setT_c(rs.getString("t_c"));
                tmp.setTel_numarasi(rs.getString("tel_numarasi"));
                tmp.setHastalar(this.getHastaDAO().getHastalar(tmp.getId_dok()));

                dlist.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dlist;

    }

    public int count() {
        int count = 0;       
        try {

            PreparedStatement pst = this.getC().prepareStatement("select count(id_dok)as doktor_count from doktor");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("doktor_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;

    }

    public doktor find(Long id_dok) {
        doktor d = null;
        try {

           PreparedStatement st = this.getC().prepareStatement("select * from doktor where id_dok=?");
            st.setLong(1, id_dok);
            ResultSet rs = st.executeQuery();
            rs.next();
            d = new doktor();
            d.setId_dok(rs.getLong("id_dok"));
            d.setIsim(rs.getString("isim"));
            d.setT_c(rs.getString("t_c"));
            d.setTel_numarasi(rs.getString("tel_numarasi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return d;

    }

   
    public void insert(doktor doktor) {
        try {
           

            PreparedStatement pst = this.getC().prepareStatement("insert into doktor(isim ,t_c  ,tel_numarasi) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, doktor.getIsim());
            pst.setString(2, doktor.getT_c());
            pst.setString(3, doktor.getTel_numarasi());

            pst.executeUpdate();
            Long id_dok = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id_dok = gk.getLong(1);

            }
            for (hasta h : doktor.getHastalar()) {

           
                pst = this.getC().prepareStatement("insert into dok_hasta (id_dok , id_hasta) values (?,?)");
                pst.setLong(1, id_dok);
                pst.setLong(2, h.getId_hasta());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
        }
    }

    public void edit(doktor doktor) {
        try {
          

            PreparedStatement pst = this.getC().prepareStatement("update doktor set isim=? ,t_c =? ,tel_numarasi=?  where id_dok=?");
            pst.setString(1, doktor.getIsim());
            pst.setString(2, doktor.getT_c());
            pst.setString(3, doktor.getTel_numarasi());
            pst.setLong(4, doktor.getId_dok());

            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from dok_hasta where id_dok=?");
            pst.setLong(1, doktor.getId_dok());
            pst.executeUpdate();

            for (hasta h : doktor.getHastalar()) {

                pst = this.getC().prepareStatement("insert into dok_hasta (id_dok , id_hasta) values (?,?)");
                pst.setLong(1, doktor.getId_dok());
                pst.setLong(2, h.getId_hasta());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
        }
    }

    public void delete(doktor doktor) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from dok_hasta where id_dok=?");
            pst.setLong(1, doktor.getId_dok());
            pst.executeUpdate();

            pst = this.getC().prepareStatement("delete from doktor where id_dok=?");
            pst.setLong(1, doktor.getId_dok());
            pst.executeUpdate();

        } catch (SQLException ex) {
        }

    }

    
    public List<doktor> getDoktorlar(Long id_hasta) {
        List<doktor> doktorlar = new ArrayList<>();

        try {
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from dok_hasta where id_hasta=" + id_hasta);
            while (rs.next()) {
                doktorlar.add(this.find(rs.getLong("id_dok")));

            }
        } catch (SQLException ex) {
        }

        return doktorlar;
    }

    public DBConnection1 getDb() {
        if (this.db == null) {
            this.db = new DBConnection1();
        }
        return db;
    }

    public void setDb(DBConnection1 db) {
        this.db = db;
    }

    public Connection getC() {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public hastaDAO getHastaDAO() {
        if (this.hastaDAO == null) {
            this.hastaDAO = new hastaDAO();
        }
        return hastaDAO;
    }

    public void setHastaDAO(hastaDAO hastaDAO) {
        this.hastaDAO = hastaDAO;
    }

}
