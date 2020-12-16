/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.bolum;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import dao.bolumDAO;
import java.util.ArrayList;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped

public class BolumController implements Serializable {

    private List<bolum> bList;
    private bolumDAO bdao;
    private bolum bolum;

    
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
        this.pageCount = (int) Math.ceil(this.getBdao().count() / (double) pageSize);
        return pageCount;
    }
    
    public String updateForm(bolum bolum) {

        this.bolum = bolum;
        return "bolum";
    }
    public String clearForm(){
    this.bolum=new bolum();
    return "bolum";
    
    }
public String deleteConfirm(bolum bolum){
    this.bolum= bolum;
    return "confirm_delete_bolum";
}
    public String delete() {
        this.getBdao().delete(this.bolum);
        this.bolum=new bolum();
        return "bolum";
    }

    public String update() {
        this.getBdao().update(this.bolum);
                this.bolum=new bolum();

        return "bolum";
    }

    public String create() {
        this.getBdao().insert(this.bolum);
                this.bolum=new bolum();

        return "bolum";
    }

    public BolumController() {
        this.bList = new ArrayList();
        bdao = new bolumDAO();
    }

    public List<bolum> getbList() {
        this.bList = this.getBdao().getbolum(page, pageSize);
        return bList;
    }

    public bolumDAO getBdao() {
        if (this.bdao == null) {
            this.bdao = new bolumDAO();
        }
        return bdao;
    }

    public void setbList(List<bolum> bList) {
        this.bList = bList;
    }

    public void setBdao(bolumDAO bdao) {
        this.bdao = bdao;
    }

    public bolum getBolum() {
        if(this.bolum==null)
            this.bolum= new bolum();
        return bolum;
    }

    public void setBolum(bolum bolum) {
        this.bolum = bolum;
    }


}
