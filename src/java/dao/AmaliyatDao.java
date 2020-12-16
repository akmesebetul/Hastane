/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.amaliyat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Util.DBConnection1;

/**
 *
 * @author Casper
 */
public class AmaliyatDao {

    private DBConnection1 db;
    private Connection c;
    private hastaDAO hastadao;
    private doktorDAO doktordao;

    public List<amaliyat> findAll(int page, int pageSize) {
        List<amaliyat> amaliyatList = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from ameliyat order by id_ameliyat asc limit " + start + "," + pageSize);
            //statement sql ifadelerini veritabanına göndermek için oluşturulan nesneler kullanılır
            ResultSet rs = pst.executeQuery();  //resultset veritabanından veri çekme işlemi yaparken kullanılır
            while (rs.next()) {
                amaliyat tmp = new amaliyat();
                tmp.setId_ameliyat(rs.getLong("id_ameliyat"));
                tmp.setAmeliyat_ismi(rs.getString("ameliyat_ismi"));
                tmp.setAmeliyat_tarihi(rs.getString("ameliyat_tarihi"));

                tmp.setHasta(this.getHastaDAO().find(rs.getLong("id_hasta")));
                tmp.setDoktor(this.getDoktorDAO().find(rs.getLong("id_dok")));
                amaliyatList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return amaliyatList;
    }

    public int count() {
        int count = 0;
        try {
            Statement st = this.getC().createStatement();
            ResultSet rs = st.executeQuery("select count(id_ameliyat) as ameliyat_count from ameliyat");
            rs.next();
            count = rs.getInt("ameliyat_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public hastaDAO getHastaDAO() {
        if (this.hastadao == null) {
            this.hastadao = new hastaDAO();
        }
        return hastadao;
    }

    public doktorDAO getDoktorDAO() {
        if (this.doktordao == null) {
            this.doktordao = new doktorDAO();
        }
        return doktordao;
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

    public void create(amaliyat amaliyat) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("insert into ameliyat(ameliyat_ismi ,ameliyat_tarihi ,id_hasta ,id_dok) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, amaliyat.getAmeliyat_ismi());
            pst.setString(2, amaliyat.getAmeliyat_tarihi());
            pst.setLong(3, amaliyat.getHasta().getId_hasta());
            pst.setLong(4, amaliyat.getDoktor().getId_dok());

            pst.executeUpdate();
            Long id_ameliyat = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id_ameliyat = gk.getLong(1);

            }

        } catch (SQLException ex) {
        }
    }

    public void edit(amaliyat amaliyat) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("update ameliyat set ameliyat_ismi=? ,ameliyat_tarihi=? ,id_hasta=? ,id_dok=? where id_ameliyat=? ");
            pst.setString(1, amaliyat.getAmeliyat_ismi());
            pst.setString(2, amaliyat.getAmeliyat_tarihi());
            pst.setLong(3, amaliyat.getHasta().getId_hasta());
            pst.setLong(4, amaliyat.getDoktor().getId_dok());
            pst.setLong(5, amaliyat.getId_ameliyat());

            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public void remove(amaliyat amaliyat) {
        try {
            PreparedStatement pst = this.getC().prepareStatement(" delete from ameliyat where id_ameliyat=? ");
            pst.setLong(1, amaliyat.getId_ameliyat());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
