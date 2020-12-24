/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.room;
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
public class RoomDao {
      private DBConnection1 db;
     private Connection c ;
     private hastaDAO hastadao;
     

    public List<room> findAll(int page, int pageSize) {
        List<room> roomList = new ArrayList<>();
        int start =(page-1)*pageSize;
        try {
        PreparedStatement pst = this.getC().prepareStatement("select * from room order by id_oda asc limit "+start+" ,"+pageSize);
         ResultSet rs = pst.executeQuery();
         while ( rs.next()){
         room tmp = new room();
         tmp.setId_oda(rs.getLong("id_oda"));
         tmp.setKat(rs.getString("kat"));
         tmp.setOda_numarasi(rs.getInt("oda_numarasi"));
        
         tmp.setHasta(this.getHastaDAO().find(rs.getLong("id_hasta")));
        
         roomList.add(tmp);
         } 
        }catch(SQLException ex){
          System.out.println(ex.getMessage());
        }
        
       return roomList;
    }
      public int count () {
         int count=0;
        try {
         Statement st = this.getC().createStatement();
         ResultSet rs = st.executeQuery( "select count(id_oda)as room_count from room");
         rs.next();
         count = rs.getInt("room_count");
        
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

    public void create(room room) {
         try {

            PreparedStatement pst = this.getC().prepareStatement("insert into room(kat ,oda_numarasi ,id_hasta) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, room.getKat());
            pst.setInt(2, room.getOda_numarasi());
            pst.setLong(3,room.getHasta().getId_hasta());
            

            pst.executeUpdate();
            Long id_oda = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id_oda = gk.getLong(1);

            }
            
        } catch (SQLException ex) {
        }
    }

    public void edit(room room) {
        try {

            PreparedStatement pst = this.getC().prepareStatement("update room set kat=? ,oda_numarasi=? ,id_hasta=? where id_oda=? ");
            pst.setString(1, room.getKat());
            pst.setInt(2, room.getOda_numarasi());
            pst.setLong(3,room.getHasta().getId_hasta());
            
            pst.setLong(4, room.getId_oda());

            pst.executeUpdate();
           
            
            
        } catch (SQLException ex) {
        }
    }

    public void remove(room room) {
        try{
           PreparedStatement pst = this.getC().prepareStatement(" delete from room where id_oda=? ");
            pst.setLong(1, room.getId_oda());
            pst.executeUpdate();
       }catch (SQLException ex){
        System.out.println(ex.getMessage());
       }  
    }
    
}
