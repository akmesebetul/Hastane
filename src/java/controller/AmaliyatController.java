/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AmaliyatDao;
import dao.doktorDAO;
import dao.hastaDAO;
import entity.amaliyat;
import entity.doktor;
import entity.hasta;
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

public class AmaliyatController implements Serializable {

    private amaliyat amaliyat;
    private List<amaliyat> amaliyatList;
    private AmaliyatDao amaliyatdao;

    private hastaDAO hastadao;
    private List<hasta> hastaList;

    private doktorDAO doktordao;
    private List<doktor> doktorList;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public void delete() {
        this.getAmaliyatdao().remove(this.amaliyat);
        this.clearForm();
    }

    public void clearForm() {
        this.amaliyat = new amaliyat();
    }

    public void updateForm(amaliyat m) {
        this.amaliyat = m;

    }

    public void update() {
        this.getAmaliyatdao().edit(this.amaliyat);
        this.clearForm();
    }

    public void create() {
        this.getAmaliyatdao().create(this.amaliyat);
        this.clearForm();

    }

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

    public amaliyat getAmaliyat() {
        if (this.amaliyat == null) {
            this.amaliyat = new amaliyat();
        }
        return amaliyat;
    }

    public void setAmaliyat(amaliyat amaliyat) {
        this.amaliyat = amaliyat;
    }

    public List<amaliyat> getAmaliyatList() {
        this.amaliyatList = this.getAmaliyatdao().findAll(page, pageSize);
        return amaliyatList;
    }

    public void setAmaliyatList(List<amaliyat> amaliyatList) {
        this.amaliyatList = amaliyatList;
    }

    public AmaliyatDao getAmaliyatdao() {
        if (this.amaliyatdao == null) {
            this.amaliyatdao = new AmaliyatDao();
        }
        return amaliyatdao;
    }

    public void setAmaliyatdao(AmaliyatDao amaliyatdao) {
        this.amaliyatdao = amaliyatdao;
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
        this.pageCount = (int) Math.ceil(this.getAmaliyatdao().count() / (double) pageSize);
        return pageCount;

    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
