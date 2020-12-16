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
import Util.DBConnection;

/**
 *
 * @author Casper
 */
public class hastaDAO {

    private DBConnection db;
    private Connection c;
    private doktorDAO doktorDAO;

    public List<hasta> gethasta(int page, int pageSize) {
        List<hasta> hlist = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {

            PreparedStatement pst = this.getC().prepareStatement("select * from hasta order by id_hasta asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                hasta tmp = new hasta();
                tmp.setId_hasta(rs.getLong("id_hasta"));
                tmp.setIsim(rs.getString("isim"));
                tmp.setT_c(rs.getString("t_c"));
                tmp.setD_tarihi(rs.getString("d_tarihi"));
                tmp.setTel_numarasi(rs.getString("tel_numarasi"));
                tmp.setDoktorlar(this.getDoktorDAO().getDoktorlar(tmp.getId_hasta()));
                hlist.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return hlist;

    }

    public int count() {
        int count = 0;
        try {

            PreparedStatement pst = this.getC().prepareStatement("select count(id_hasta)as hasta_count from hasta");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("hasta_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;

    }

    public hasta find(Long id_hasta) {
        hasta h = null;
        try {

            PreparedStatement st = this.getC().prepareStatement("select * from hasta where id_hasta=?");
            st.setLong(1, id_hasta);
            ResultSet rs = st.executeQuery();
            rs.next();
            h = new hasta();
            h.setId_hasta(rs.getLong("id_hasta"));
            h.setIsim(rs.getString("isim"));
            h.setT_c(rs.getString("t_c"));
            h.setD_tarihi(rs.getString("d_tarihi"));
            h.setTel_numarasi(rs.getString("tel_numarasi"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return h;

    }

    public List<hasta> getHastalar(Long id_dok) {
        List<hasta> hastalar = new ArrayList<>();

        try {
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select * from dok_hasta where id_dok=" + id_dok);
            while (rs.next()) {
                hastalar.add(this.find(rs.getLong("id_hasta")));

            }
        } catch (SQLException ex) {
        }

        return hastalar;
    }

    public void insert(hasta hasta) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("insert into hasta(isim ,t_c ,d_tarihi ,tel_numarasi) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, hasta.getIsim());
            pst.setString(2, hasta.getT_c());
            pst.setString(3, hasta.getD_tarihi());
            pst.setString(4, hasta.getTel_numarasi());

            pst.executeUpdate();
            Long id_hasta = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id_hasta = gk.getLong(1);

            }
            for (doktor d : hasta.getDoktorlar()) {

                pst = this.getC().prepareStatement("insert into ilac (id_hasta , id_dok) values (?,?)");
                pst.setLong(1, id_hasta);
                pst.setLong(2, d.getId_dok());
                pst.executeUpdate();

            }
        } catch (SQLException ex) {
        }
    }

    public void edit(hasta hasta) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("update hasta set isim=? ,t_c =? ,d_tarihi=?,tel_numarasi=?  where id_hasta=?");
            pst.setString(1, hasta.getIsim());
            pst.setString(2, hasta.getT_c());
            pst.setString(3, hasta.getD_tarihi());
            pst.setString(4, hasta.getTel_numarasi());
            pst.setLong(5, hasta.getId_hasta());

            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from dok_hasta where id_hasta=?");
            pst.setLong(1, hasta.getId_hasta());
            pst.executeUpdate();

            for (doktor d : hasta.getDoktorlar()) {

                pst = this.getC().prepareStatement("insert into dok_hasta (id_hasta , id_dok) values (?,?)");
                pst.setLong(1, hasta.getId_hasta());
                pst.setLong(2, d.getId_dok());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
        }
    }

    public void delete(hasta hasta) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("delete from dok_hasta where id_hasta=?");
            pst.setLong(1, hasta.getId_hasta());
            pst.executeUpdate();
            pst = this.getC().prepareStatement("delete from hasta where id_hasta=?");
            pst.setLong(1, hasta.getId_hasta());
            pst.executeUpdate();

        } catch (SQLException ex) {
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

    public doktorDAO getDoktorDAO() {
        if (this.doktorDAO == null) {
            this.doktorDAO = new doktorDAO();
        }
        return doktorDAO;
    }
}
