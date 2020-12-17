/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.ozel_hizmet;
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
public class Ozel_hizmetDao {
      private DBConnection1 db;
     private Connection c ;
     private hastaDAO hastadao;
     

    public List<ozel_hizmet> findAll(int page, int pageSize) {
        List<ozel_hizmet> ozel_hizmetList = new ArrayList<>();
        int start =(page-1)*pageSize;
        try {
          PreparedStatement pst = this.getC().prepareStatement("select * from ozel_hizmet order by id_hizmet asc limit "+start+" ,"+pageSize);
         ResultSet rs = pst.executeQuery();
         while ( rs.next()){
         ozel_hizmet tmp = new ozel_hizmet();
         tmp.setId_hizmet(rs.getLong("id_hizmet"));
         tmp.setHizmet_adi(rs.getString("hizmet_adi"));
        
        
         tmp.setHasta(this.getHastaDAO().find(rs.getLong("id_hasta")));
        
         ozel_hizmetList.add(tmp);
         } 
        }catch(SQLException ex){
          System.out.println(ex.getMessage());
        }
        
       return ozel_hizmetList;
    }
     public int count () {
         int count=0;
        try {
         Statement st = this.getC().createStatement();
         ResultSet rs = st.executeQuery( "select count(id_hizmet)as ozel_hizmet_count from ozel_hizmet");
         rs.next();
         count = rs.getInt("ozel_hizmet_count");
        
         } 
        catch(SQLException ex){
          System.out.println(ex.getMessage());
        }
        
       return count;
    }

    public hastaDAO getHastaDAO() {
        if ( this.hastadao==null)
            this.hastadao= new hastaDAO();
        return hastadao;
    }

   
    

    
    public DBConnection1 getDb() {
        if ( this.db==null)
            this.db= new DBConnection1();
        return db;
    }

    public void setDb(DBConnection1 db) {
       this.db = db;
    }

    public Connection getC() {
         if ( this.c==null)
            this.c= this.getDb().connect();
        return c;
    }

    public void create(ozel_hizmet ozel_hizmet) {
         try {

            PreparedStatement pst = this.getC().prepareStatement("insert into ozel_hizmet(hizmet_adi,id_hasta) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, ozel_hizmet.getHizmet_adi());
            pst.setLong(2,ozel_hizmet.getHasta().getId_hasta());
            pst.executeUpdate();
            Long id_hizmet = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id_hizmet = gk.getLong(1);

            }
            
        } catch (SQLException ex) {
        }
    }

    public void edit(ozel_hizmet ozel_hizmet) {
         try {

            PreparedStatement pst = this.getC().prepareStatement("update ozel_hizmet set hizmet_adi=?,id_hasta=? where id_hizmet=? ");
            pst.setString(1, ozel_hizmet.getHizmet_adi());
            
            pst.setLong(2,ozel_hizmet.getHasta().getId_hasta());
           
            pst.setLong(3, ozel_hizmet.getId_hizmet() );

            pst.executeUpdate();
           
            
            
        } catch (SQLException ex) {
        }
    }

    public void remove(ozel_hizmet ozel_hizmet) {
         try{
           PreparedStatement pst = this.getC().prepareStatement(" delete from ozel_hizmet where id_hizmet=? ");
            pst.setLong(1, ozel_hizmet.getId_hizmet());
            pst.executeUpdate();
       }catch (SQLException ex){
        System.out.println(ex.getMessage());
       }    
    }
    
}
