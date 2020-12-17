/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.hastaDAO;
import dao.Ozel_hizmetDao;
import entity.hasta;
import entity.ozel_hizmet;
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
public class Ozel_hizmetController implements Serializable {
      private ozel_hizmet ozel_hizmet;
    private List<ozel_hizmet> ozel_hizmetList;
    private Ozel_hizmetDao ozel_hizmetdao;
     
    private hastaDAO hastadao;
    private List<hasta> hastaList;
     private int page=1;
    private int pageSize=10;
    private int pageCount;
    
     public void delete(){
    this.getOzel_hizmetdao().remove(this.ozel_hizmet);
     this.clearForm();
    
    }
       public void clearForm(){
       this.ozel_hizmet= new ozel_hizmet();
    }
    
    
     public void update() {
     this.getOzel_hizmetdao().edit(this.ozel_hizmet);
      this.clearForm();
    }
      public void create() {
        this.getOzel_hizmetdao().create(this.ozel_hizmet);
         this.clearForm();
        

    }
      public void next(){
        if (this.page==this.getPageCount())
            this.page=1;
        else
    this.page++;
    }
    public void previous(){
        if ( this.page==1)
            this.page=this.getPageCount();
        
        else
                 
        this.page--;
            
        
    }

    public ozel_hizmet getOzel_hizmet() {
        if( this.ozel_hizmet==null)
            this.ozel_hizmet=new ozel_hizmet();
        return ozel_hizmet;
    }

    public void setOzel_hizmet(ozel_hizmet ozel_hizmet) {
        this.ozel_hizmet = ozel_hizmet;
    }

    public List<ozel_hizmet> getOzel_hizmetList() {
        this.ozel_hizmetList= this.getOzel_hizmetdao().findAll(page,pageSize);
        return ozel_hizmetList;
    }

    public void setOzel_hizmetList(List<ozel_hizmet> ozel_hizmetList) {
        this.ozel_hizmetList = ozel_hizmetList;
    }

  

    public Ozel_hizmetDao getOzel_hizmetdao() {
         if( this.ozel_hizmetdao==null)
            this.ozel_hizmetdao=new Ozel_hizmetDao();
        return ozel_hizmetdao;
    }

    public void setOzel_hizmetdao(Ozel_hizmetDao ozel_hizmetdao) {
        this.ozel_hizmetdao = ozel_hizmetdao;
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
        this.pageCount = (int) Math.ceil(this.getOzel_hizmetdao().count() / (double) pageSize);
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
