/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.hastaDAO;
import dao.RoomDao;
import entity.hasta;
import entity.room;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped
public class RoomController implements Serializable {
     private room room;
    private List<room> roomList;
    private RoomDao roomdao;
     private int page=1;
    private int pageSize=10;
    private int pageCount;
    
    private hastaDAO hastadao;
    private List<hasta> hastaList;
    
     public void delete(){
    this.getRoomdao().remove(this.room);
     this.clearForm();
    
    }
      public void clearForm(){
      this.room= new room();
    }
    
    public void update() {
     this.getRoomdao().edit(this.room);
      this.clearForm();
    }
      public void create() {
        this.getRoomdao().create(this.room);
         this.clearForm();
        

    }
      public void next(){
        if (this.page==this.getPageCount()){
            this.page=1;}
        else{
    this.page++;}
    }
    public void previous(){
        if ( this.page==1)
            this.page=this.getPageCount();
        
        else
                 
        this.page--;
            
        
    }

    public room getRoom() {
        if( this.room==null)
            this.room=new room();
        return room;
    }

    public void setRoom(room room) {
        this.room = room;
    }

    public List<room> getRoomList() {
        this.roomList= this.getRoomdao().findAll(page,pageSize);
        return roomList;
    }

    public void setRoomList(List<room> roomList) {
        this.roomList = roomList;
    }

  

    public RoomDao getRoomdao() {
         if( this.roomdao==null)
            this.roomdao=new RoomDao();
        return roomdao;
    }

    public void setRoomdao(RoomDao roomdao) {
        this.roomdao = roomdao;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getRoomdao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

  

   

    public hastaDAO getHastaDAO() {
          if( this.hastadao== null)
            this.hastadao= new hastaDAO();
        return hastadao;
    }

    public void setHastadao(hastaDAO hastadao) {
        this.hastadao = hastadao;
    }

    public List<hasta> getHastaList() {
         this.hastaList= this.getHastaDAO().gethasta(1, 10);
        return hastaList;
    }

    public void setHastaList(List<hasta> hastaList) {
        this.hastaList = hastaList;
    }
    
    
}
