/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.fatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.DBConnection;

/**
 *
 * @author Casper
 */
public class faturaDAO {

    private DBConnection db;
    private Connection c;
    private hastaDAO hastaDAO;//1

    public List<fatura> getrendevu() {
        List<fatura> fList = new ArrayList<>();

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from fatura");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                fatura tmp = new fatura();
                tmp.setId_fatura(rs.getInt("id_fatura"));
                tmp.setFatura_tarihi(rs.getString("fatura_tarihi"));
                tmp.setMiktar(rs.getInt("miktar"));

                rs.getLong("id_hasta");//3
                tmp.setH(this.getHastaDAO().find(rs.getLong("id_hasta")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return fList;

    }

    public void delete(fatura fatura) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from fatura where id_fatura=?");
            pst.setInt(1, fatura.getId_fatura());
            pst.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public void create(fatura fatura) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into fatura (fatura_tarihi,id_hasta) values (?,?)");
            pst.setString(1, fatura.getFatura_tarihi());
            pst.setLong(2, fatura.getH().getId_hasta());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void edit(fatura fatura) {

        try {
            PreparedStatement pst = this.getC().prepareStatement("update fatura set fatura_tarihi=? ,id_hasta=? where id_fatura=?");
            pst.setString(1, fatura.getFatura_tarihi());
            pst.setLong(2, fatura.getH().getId_hasta());
            pst.setLong(3, fatura.getId_fatura());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DBConnection getDb() {
        return db;
    }

    public void setDb(DBConnection db) {
        this.db = db;
    }

    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public hastaDAO getHastaDAO() {
        return hastaDAO;
    }

    public void setHastaDAO(hastaDAO hastaDAO) {
        this.hastaDAO = hastaDAO;
    }

}
