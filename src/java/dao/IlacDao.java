/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.ilac;
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
public class IlacDao {
     private DBConnection1 db;
     private Connection c ;
     private hastaDAO hastadao;
     private doktorDAO doktordao;

    public List<ilac> findAll(int page, int pageSize) {
        List<ilac> ilacList = new ArrayList<>();
        int start =(page-1)*pageSize;
        try {
         PreparedStatement pst = this.getC().prepareStatement("select * from ilac order by id_ilac asc limit "+start+" ,"+pageSize);
         ResultSet rs = pst.executeQuery();
         while ( rs.next()){
         ilac tmp = new ilac();
         tmp.setId_ilac(rs.getLong("id_ilac"));
         tmp.setIlac_adi(rs.getString("ilac_adi"));
          tmp.setDoktor(this.getDoktordao().find(rs.getLong("id_dok")));
           tmp.setHasta(this.getHastaDAO().find(rs.getLong("id_hasta")));
         ilacList.add(tmp);
         } 
        }catch(SQLException ex){
          System.out.println(ex.getMessage());
        }
        
       return ilacList;
    }
     public int count () {
         int count=0;
        try {
         Statement st = this.getC().createStatement();
         ResultSet rs = st.executeQuery( "select count(id_dok_hasta)as ilac_count from ilac");
         rs.next();
         count = rs.getInt("ilac_count");
        
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

    public doktorDAO getDoktordao() {
         if ( this.doktordao==null)
            this.doktordao= new doktorDAO();
        return doktordao;
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

   

   

    public void create(ilac ilac) {
          try {

            PreparedStatement pst = this.getC().prepareStatement("insert into ilac(ilac_adi ,id_dok ,id_hasta) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, ilac.getIlac_adi());
             pst.setLong(2,ilac.getDoktor().getId_dok());
             pst.setLong(3,ilac.getHasta().getId_hasta());
            

            pst.executeUpdate();
            Long id_ilac = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id_ilac = gk.getLong(1);

            }
            
        } catch (SQLException ex) {
        }
    }

    public void edit(ilac ilac) {
         try {

            PreparedStatement pst = this.getC().prepareStatement("update ilac set ilac_adi=? ,id_dok=? ,id_hasta=? where id_ilac=? ");
            pst.setString(1, ilac.getIlac_adi());
            pst.setLong(2,ilac.getDoktor().getId_dok());
            pst.setLong(3,ilac.getHasta().getId_hasta());
            pst.setLong(4, ilac.getId_ilac());

            pst.executeUpdate();
           
            
            
        } catch (SQLException ex) {
        }
    }

    public void remove(ilac ilac) {
       try{
           PreparedStatement pst = this.getC().prepareStatement(" delete from ilac where id_ilac=? ");
            pst.setLong(1, ilac.getId_ilac());
            pst.executeUpdate();
       }catch (SQLException ex){
        System.out.println(ex.getMessage());
       }        
    }
    
}
