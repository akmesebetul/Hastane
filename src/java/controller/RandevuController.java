/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.bolumDAO;
import dao.doktorDAO;
import dao.hastaDAO;
import entity.randevu;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import dao.randevuDAO;
import entity.bolum;
import entity.doktor;
import entity.hasta;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped

public class RandevuController implements Serializable {

    private List<randevu> rList;
    private randevuDAO randevudao;
    private randevu randevu;

    private hastaDAO hastaDAO;//2222
    private List<hasta> hastalist;//3
    private doktorDAO doktorDAO;
    private List<doktor> doktorlist;
    private bolumDAO bolumDAO;
    private List<bolum> bolumlist;

    /* public String deleteConfirm(randevu randevu) {
        this.randevu = randevu;
        return "confirm_delete_randevu";
    }

    public String show(randevu randevu) {
        this.randevu = randevu;
        return "randevu";

    }
    
*/
    
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
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
 this.pageCount = (int) Math.ceil(this.getRandevudao().count() / (double) pageSize);
        return pageCount;    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void updateForm(randevu randevu) {

        this.randevu = randevu;
       
    }
    public void update(){
    
            this.getRandevudao().edit(this.randevu);
                           this.clearForm();

}

    public void clearForm() {
        this.randevu = new randevu();

    }

   public void delete() {
        this.getRandevudao().delete(this.randevu);
               this.clearForm();


    }

    public void create() {
        this.getRandevudao().create(this.randevu);
               this.clearForm();

    }
     
    public hastaDAO getHastaDAO() {
        if (this.hastaDAO == null) {
            this.hastaDAO = new hastaDAO();
        }
        return hastaDAO;
    }

    public List<hasta> getHastalist() {
        this.hastalist = this.getHastaDAO().gethasta(1, 10);
        return hastalist;
    }

    public void setHastalist(List<hasta> hastalist) {
        this.hastalist = hastalist;
    }

    public doktorDAO getDoktorDAO() {
        if (this.doktorDAO == null) {
            this.doktorDAO = new doktorDAO();
        }
        return doktorDAO;
    }

    public void setDoktorDAO(doktorDAO doktorDAO) {
        this.doktorDAO = doktorDAO;
    }

    public List<doktor> getDoktorlist() {
        this.doktorlist = this.getDoktorDAO().getdoktor(1, 10);

        return doktorlist;
    }

    public void setDoktorlist(List<doktor> doktorlist) {
        this.doktorlist = doktorlist;
    }
     

    public bolumDAO getBolumDAO() {
        if (this.bolumDAO == null) {
            this.bolumDAO = new bolumDAO();
        }
        return bolumDAO;
    }

    public void setBolumDAO(bolumDAO bolumDAO) {
        this.bolumDAO = bolumDAO;
    }

    public List<bolum> getBolumlist() {
                this.bolumlist = this.getBolumDAO().getbolum(page, pageSize);

        return bolumlist;
    }

    public void setBolumlist(List<bolum> bolumlist) {
        this.bolumlist = bolumlist;
    }

    public List<randevu> getrList() {
        this.rList = this.getRandevudao().getrendevu(page, pageSize);
        return rList;
    }

    public void setrList(List<randevu> rList) {
        this.rList = rList;
    }

    public randevuDAO getRandevudao() {
        if (this.randevudao == null) {
            this.randevudao = new randevuDAO();
        }
        return randevudao;
    }

    public void setRandevudao(randevuDAO randevudao) {
        this.randevudao = randevudao;
    }

    public randevu getRandevu() {
        if (this.randevu == null) {
            this.randevu = new randevu();
        }
        return randevu;
    }

    public void setRandevu(randevu randevu) {
        this.randevu = randevu;
    }
}
