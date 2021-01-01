/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.tahliller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Util.DBConnection1;

/**
 *
 * @author Casper
 */
public class TahlillerDAO {

    private DBConnection1 db;
    private Connection c;
    private hastaDAO hastaDAO;//1
    private doktorDAO doktorDAO;//2

    public List<tahliller> gettahliller(int page, int pageSize) {
        List<tahliller> tlist = new ArrayList();
        // TODO code application logic here
                    int start = (page - 1) * pageSize;

        try {

            PreparedStatement pst = this.getC().prepareStatement("select * from tahliller");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //         System.out.println(rs.getString("isim"));
                tahliller tmp = new tahliller();

                tmp.setId_tahlil(rs.getLong("id_tahlil"));
                tmp.setTipi(rs.getString("tipi"));
                rs.getLong("id_hasta");//3
                tmp.setH(this.getHastaDAO().find(rs.getLong("id_hasta")));
                rs.getLong("id_dok");
                tmp.setD(this.getDoktorDAO().find(rs.getLong("id_dok")));//4

                tlist.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tlist;

    }
    public int count() {
        int count = 0;        // TODO code application logic here
        try {

            PreparedStatement pst = this.getC().prepareStatement("select count(id_tahlil)as tahliller_count from tahliller");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("tahliller_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;

    }


    /*public void delete(tahliller tahliller) {
        try {

            Statement st = this.getC().createStatement();
            st.executeUpdate("delete from tahliller where id_tahlil=" + tahliller.getId_tahlil());

        } catch (SQLException ex) {
        }
    }
     */
    public void insert(tahliller tahliller) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("insert into tahliller(tipi ,id_hasta ,id_dok ) values (?,?,?)");
            pst.setString(1, tahliller.getTipi());
            pst.setLong(2, tahliller.getH().getId_hasta());
            pst.setLong(3, tahliller.getD().getId_dok());

            pst.executeUpdate();
            /* Statement st = this.getC().createStatement();
            st.executeUpdate("insert into tahliller(tipi ,id_hasta ,id_dok ) values('" + tahliller.getTipi() + "', " + selectedHasta + " , " + selectedDoktor + ")");
             */
        } catch (SQLException ex) {
        }

    }

    public void edit(tahliller tahliller) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("update tahliller set tipi=?,id_hasta =?,id_dok =? where id_tahlil=?");
            pst.setString(1, tahliller.getTipi());
            pst.setLong(2, tahliller.getH().getId_hasta());
            pst.setLong(3, tahliller.getD().getId_dok());
            pst.setLong(4, tahliller.getId_tahlil());

            pst.executeUpdate();
            /* Statement st = this.getC().createStatement();
            st.executeUpdate("insert into tahliller(tipi ,id_hasta ,id_dok ) values('" + tahliller.getTipi() + "', " + selectedHasta + " , " + selectedDoktor + ")");
             */
        } catch (SQLException ex) {
        }
    }

    public void delete(tahliller tahliller) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from tahliller where id_tahlil=?");
            pst.setLong(1, tahliller.getId_tahlil());
            pst.executeUpdate();

            /* Statement st = this.getC().createStatement();
            st.executeUpdate("delete from randevu where id_r=" + randevu.getId_r());*/
        } catch (SQLException ex) {
        }
    }

    /* public void update(tahliller tahliller) {

        try {
            Statement st = this.getC().createStatement();
            st.executeUpdate("update tahliller  set tipi = '" + tahliller.getTipi() + "', id_hasta ='" + tahliller.getId_hasta() + "',  id_dok = '" + tahliller.getId_dok() + "'  where id_tahlil= " + tahliller.getId_tahlil());
        } catch (SQLException ex) {
        }

    }*/
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

}
