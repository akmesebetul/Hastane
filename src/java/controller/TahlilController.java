/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.tahliller;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import dao.TahlillerDAO;
import dao.doktorDAO;
import dao.hastaDAO;
import entity.doktor;
import entity.hasta;
import java.util.ArrayList;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped

public class TahlilController implements Serializable {

    private List<tahliller> tList;
    private TahlillerDAO tdao;
    private tahliller tahliller;

    private hastaDAO hastaDAO;//2222
    private List<hasta> hastalist;//3
    private doktorDAO doktorDAO;
    private List<doktor> doktorlist;

    /* public String deleteConfirm(tahliller tahliller) {
        this.tahliller = tahliller;
        return "confirm_delete_tahliller";
    }

    public String show(tahliller tahliller) {
        this.tahliller = tahliller;
        return "tahliller";

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
        this.pageCount = (int) Math.ceil(this.getTdao().count()/ (double) pageSize);
        return pageCount;
    }
    public void updateForm(tahliller tahliller) {

        this.tahliller = tahliller;

    }

    public String clearForm() {
        this.tahliller = new tahliller();
        return "tahliller";

    }

    public void update() {
        this.getTdao().edit(this.tahliller);

        this.clearForm();

    }

    public void delete() {
        this.getTdao().delete(this.tahliller);
        this.clearForm();

    }

    public String create() {
        this.getTdao().insert(this.tahliller);
        this.clearForm();

        return "tahliller";
    }

    public TahlilController() {
        this.tList = new ArrayList();
        tdao = new TahlillerDAO();
    }

    public TahlillerDAO getTdao() {
        if (this.tdao == null) {
            this.tdao = new TahlillerDAO();
        }
        return tdao;

    }

    public void settList(List<tahliller> tList) {
        this.tList = tList;
    }

    public void settdao(TahlillerDAO tdao) {
        this.tdao = tdao;
    }

    public tahliller getTahliller() {
        if (this.tahliller == null) {
            this.tahliller = new tahliller();
        }

        return tahliller;
    }

    public void setTahliller(tahliller tahliller) {
        this.tahliller = tahliller;
    }

    public List<tahliller> gettList() {
        this.tList = this.getTdao().gettahliller(page, pageSize);
        return tList;
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

    public void setTdao(TahlillerDAO tdao) {
        this.tdao = tdao;
    }

    public void setHastaDAO(hastaDAO hastaDAO) {
        this.hastaDAO = hastaDAO;
    }

}
