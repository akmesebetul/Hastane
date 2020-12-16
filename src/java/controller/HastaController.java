/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.hasta;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import dao.hastaDAO;
import entity.bolum;
import entity.doktor;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped

public class HastaController implements Serializable {

    private List<hasta> cList;
    private hastaDAO cdao;
    private hasta hasta;

    @Inject
    private DoktorController doktorcontroller;

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

    public void updateForm(hasta hasta) {

        this.hasta = hasta;

    }

    public void clearForm() {
        this.hasta = new hasta();

    }

    public void update() {
        this.getCdao().edit(this.hasta);
        this.clearForm();

    }

    public void delete() {
        this.getCdao().delete(this.hasta);
        this.clearForm();
    }

    public void create() {
        this.getCdao().insert(this.hasta);
        this.clearForm();

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
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public HastaController() {
        this.cList = new ArrayList();
        cdao = new hastaDAO();
    }

    public List<hasta> getcList() {
        this.cList = this.getCdao().gethasta(page, pageSize);
        return cList;
    }

    public hastaDAO getCdao() {
        if (this.cdao == null) {
            this.cdao = new hastaDAO();
        }
        return cdao;

    }

    public void setcList(List<hasta> cList) {
        this.cList = cList;
    }

    public void setCdao(hastaDAO cdao) {
        this.cdao = cdao;
    }

    public hasta getHasta() {
        if (this.hasta == null) {
            this.hasta = new hasta();
        }

        return hasta;
    }

    public void setHasta(hasta hasta) {
        this.hasta = hasta;
    }

    public DoktorController getDoktorcontroller() {
        return doktorcontroller;
    }

    public void setDoktorcontroller(DoktorController doktorcontroller) {
        this.doktorcontroller = doktorcontroller;
    }

}
