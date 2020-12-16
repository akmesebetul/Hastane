/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.fatura;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import dao.faturaDAO;
import dao.hastaDAO;
import entity.hasta;
import java.util.ArrayList;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped

public class FaturaController implements Serializable {

    private List<fatura> cList;
    private faturaDAO cdao;

    private fatura fatura;
    private hastaDAO hastaDAO;
    private List<hasta> hastalist;

    public void updateForm(fatura fatura) {

        this.fatura = fatura;

    }

    public void update() {

        this.getCdao().edit(this.fatura);
        this.clearForm();

    }

    public void clearForm() {
        this.fatura = new fatura();

    }

    public void delete() {
        this.getCdao().delete(this.fatura);
        this.clearForm();

    }

    public String create() {
        this.getCdao().create(this.fatura);
        this.clearForm();
        return "fatura";
    }

    public List<fatura> getcList() {
        return cList;
    }

    public void setcList(List<fatura> cList) {
        this.cList = cList;
    }

    public faturaDAO getCdao() {

        if (this.cdao == null) {
            this.cdao = new faturaDAO();
        }
        return cdao;
    }

    public void setCdao(faturaDAO cdao) {
        this.cdao = cdao;
    }

    public fatura getFatura() {
        if (this.fatura == null) {
            this.fatura = new fatura();
        }
        return fatura;
    }

    public void setFatura(fatura fatura) {
        this.fatura = fatura;
    }

    public hastaDAO getHastaDAO() {
        if (this.hastaDAO == null) {
            this.hastaDAO = new hastaDAO();
        }
        return hastaDAO;
    }

    public void setHastaDAO(hastaDAO hastaDAO) {
        this.hastaDAO = hastaDAO;
    }

    public List<hasta> getHastalist() {
        this.hastalist = this.getHastaDAO().gethasta(1, 10);

        return hastalist;
    }

    public void setHastalist(List<hasta> hastalist) {
        this.hastalist = hastalist;
    }

}
