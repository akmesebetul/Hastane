/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Casper
 */
public class fatura {

    private int id_fatura;
    private String fatura_tarihi;
    private int miktar;
    private hasta h;//1

    public fatura(int id_fatura, String fatura_tarihi, int miktar, hasta h) {
        this.id_fatura = id_fatura;
        this.fatura_tarihi = fatura_tarihi;
        this.miktar = miktar;
        this.h = h;
    }

    public fatura() {
    }

    public int getId_fatura() {
        return id_fatura;
    }

    public void setId_fatura(int id_fatura) {
        this.id_fatura = id_fatura;
    }

    public String getFatura_tarihi() {
        return fatura_tarihi;
    }

    public void setFatura_tarihi(String fatura_tarihi) {
        this.fatura_tarihi = fatura_tarihi;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public hasta getH() {
        return h;
    }

    public void setH(hasta h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "fatura{" + "id_fatura=" + id_fatura + ", fatura_tarihi=" + fatura_tarihi + ", miktar=" + miktar + ", h=" + h + '}';
    }

}
