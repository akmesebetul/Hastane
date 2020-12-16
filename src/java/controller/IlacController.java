/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.IlacDao;
import dao.doktorDAO;
import dao.hastaDAO;
import entity.doktor;
import entity.hasta;
import entity.ilac;
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
public class IlacController implements Serializable{
      private ilac ilac;
    private List<ilac> ilacList;
    private IlacDao ilacdao;
    
    private hastaDAO hastadao;
    private List<hasta> hastaList;
    
    private doktorDAO doktordao;
    private List<doktor> doktorList;
    
  
    private int page=1;
    private int pageSize=10;
    private int pageCount;
    
     public void delete(){
    this.getIlacdao().remove(this.ilac);
     this.clearForm();
   
    }
     public void clearForm(){
    this.ilac= new ilac();
    }
    
     public void update() {
     this.getIlacdao().edit(this.ilac);
      this.clearForm();
    }
      public void create(){
        this.getIlacdao().create(this.ilac);
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

    public ilac Ilac() {
        if( this.ilac==null)
            this.ilac=new ilac();
        return ilac;
    }

    public void setIlac(ilac ilac) {
        this.ilac = ilac;
    }

    public List<ilac> getIlacList() {
         this.ilacList= this.getIlacdao().findAll(page,pageSize);
        return ilacList;
    }

    public void IlacList(List<ilac> ilacList) {
        this.ilacList = ilacList;
    }

  

    public IlacDao getIlacdao() {
         if( this.ilacdao==null)
            this.ilacdao=new IlacDao();
        return ilacdao;
    }

    public void setIlacdao(IlacDao ilacdao) {
        this.ilacdao = ilacdao;
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
          this.pageCount = (int) Math.ceil(this.getIlacdao().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

   
    

    public hastaDAO getHastaDAO() {
        if (this.hastadao == null) {
            this.hastadao = new hastaDAO();
        }
        return hastadao;
    }
    

    public void setHastadao(hastaDAO hastadao) {
        this.hastadao = hastadao;
    }

    public List<hasta> getHastaList() {
        this.hastaList = this.getHastaDAO().gethasta(1, 10);
        return hastaList;
    }

    public void setHastaList(List<hasta> hastaList) {
        this.hastaList = hastaList;
    }

  

    public doktorDAO getDoktorDAO() {
        if (this.doktordao == null) {
            this.doktordao = new doktorDAO();
        }
        return doktordao;
    }

    public void setDoktordao(doktorDAO doktordao) {
        this.doktordao = doktordao;
    }

    public List<doktor> getDoktorList() {
        this.doktorList = this.getDoktorDAO().getdoktor(1, 10);
        return doktorList;
    }

    public void setDoktorList(List<doktor> doktorList) {
        this.doktorList = doktorList;
    }

    

   
    
    
    
}
