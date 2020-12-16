/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.doktor;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import dao.doktorDAO;
import entity.hasta;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped

public class DoktorController implements Serializable {

    private List<doktor> dList;
    private doktorDAO cdao;
    private doktor doktor;
    
    private List<Long> selectedhastas;
    
    @Inject
    private HastaController hastacontroller;

    
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
 this.pageCount = (int) Math.ceil(this.getCdao().count() / (double) pageSize);
        return pageCount;    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public void updateForm(doktor doktor) {

        this.doktor = doktor;
    }

    public void clearForm() {
        this.doktor = new doktor();

    }

    public void update() {
        this.getCdao().edit(this.doktor);
        this.clearForm();
    }

    public void delete() {
        this.getCdao().delete(this.doktor);
        this.clearForm();
    }

    public void create() {
        this.getCdao().insert(this.doktor);
        this.clearForm();
    }

    public DoktorController() {
        this.dList = new ArrayList();
        cdao = new doktorDAO();
    }

    public List<doktor> getdList() {
        this.dList = this.getCdao().getdoktor(page, pageSize);
        return dList;
    }

    public doktorDAO getCdao() {
        if (this.cdao == null) {
            this.cdao = new doktorDAO();
        }
        return cdao;
    }

    public void setdList(List<doktor> dList) {
        this.dList = dList;
    }

    public void setCdao(doktorDAO cdao) {
        this.cdao = cdao;
    }

    public doktor getDoktor() {
        if (this.doktor == null) {
            this.doktor = new doktor();
        }

        return doktor;
    }

    public void setDoktor(doktor doktor) {
        this.doktor = doktor;
    }

    public HastaController getHastacontroller() {
        return hastacontroller;
    }

    public void setHastacontroller(HastaController hastacontroller) {
        this.hastacontroller = hastacontroller;
    }

    public List<Long> getSelectedhastas() {
        return selectedhastas;
    }

    public void setSelectedhastas(List<Long> selectedhastas) {
        this.selectedhastas = selectedhastas;
    }
    

}
