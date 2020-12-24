/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.randevu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Util.DBConnection;

/**
 *
 * @author Casper
 */
public class randevuDAO {

    private DBConnection db;
    private Connection c;
    private hastaDAO hastaDAO;//1
    private doktorDAO doktorDAO;//2
    private bolumDAO bolumDAO;

    public List<randevu> getrendevu(int page, int pageSize) {
        List<randevu> rList = new ArrayList<>();
        // TODO code application logic here
                    int start = (page - 1) * pageSize;

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from randevu order by id_r asc limit "+start + "," + pageSize);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //         System.out.println(rs.getString("isim"));
                randevu tmp = new randevu();
                tmp.setId_r(rs.getLong("id_r"));
                tmp.setTarih(rs.getString("tarih"));
                rs.getLong("id_hasta");//3
                tmp.setH(this.getHastaDAO().find(rs.getLong("id_hasta")));
                rs.getLong("id_dok");
                tmp.setD(this.getDoktorDAO().find(rs.getLong("id_dok")));//4
                rs.getLong("id_bolum");
                tmp.setB(this.getBolumDAO().find(rs.getLong("id_bolum")));//4
                rList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rList;

    }

    public int count() {
        int count = 0;        // TODO code application logic here
        try {

            PreparedStatement pst = this.getC().prepareStatement("select count(id_r)as randevu_count from randevu");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("randevu_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;

    }
    public void delete(randevu randevu) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from randevu where id_r=?");
            pst.setLong(1, randevu.getId_r());
            pst.executeUpdate();

            /* Statement st = this.getC().createStatement();
            st.executeUpdate("delete from randevu where id_r=" + randevu.getId_r());*/
        } catch (SQLException ex) {
        }
    }

    public void create(randevu randevu) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into randevu (Tarih ,id_hasta ,id_dok , id_bolum) values (?,?,?,?)");
            pst.setString(1, randevu.getTarih());
            pst.setLong(2, randevu.getH().getId_hasta());
            pst.setLong(3, randevu.getD().getId_dok());
            pst.setLong(4, randevu.getB().getId_bolum());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void edit(randevu randevu) {

        try {
            PreparedStatement pst = this.getC().prepareStatement("update randevu set Tarih=? ,id_hasta=?,id_dok=? , id_bolum=? where id_r=?");
            pst.setString(1, randevu.getTarih());
            pst.setLong(2, randevu.getH().getId_hasta());
            pst.setLong(3, randevu.getD().getId_dok());
            pst.setLong(4, randevu.getB().getId_bolum());
            pst.setLong(5, randevu.getId_r());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DBConnection getDb() {
        if (this.db == null) {
            this.db = new DBConnection();
        }
        return db;
    }

    public Connection getC() {
        if (this.c == null) {
            this.c = this.getDb().connect();
        }
        return c;
    }

    public hastaDAO getHastaDAO() {//5
        if (this.hastaDAO == null) {
            this.hastaDAO = new hastaDAO();
        }
        return hastaDAO;
    }

    public void setHastaDAO(hastaDAO hastaDAO) {
        this.hastaDAO = hastaDAO;
    }

    public doktorDAO getDoktorDAO() {//6
        if (this.doktorDAO == null) {
            this.doktorDAO = new doktorDAO();
        }
        return doktorDAO;
    }

    public void setDoktorDAO(doktorDAO doktorDAO) {
        this.doktorDAO = doktorDAO;
    }

    public bolumDAO getBolumDAO() {
        if (this.bolumDAO == null) {
            this.bolumDAO = new bolumDAO();
        }
        return bolumDAO;
    }

    public void setBolumDAO(bolumDAO bolumDAO) {
        this.bolumDAO = bolumDAO;
    }

    public void setDb(DBConnection db) {
        this.db = db;
    }

    public void setC(Connection c) {
        this.c = c;
    }

}
