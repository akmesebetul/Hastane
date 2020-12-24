/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Casper
 */
public class randevu {

    private Long id_r;
    private String Tarih;
    private hasta h;
    private doktor d;
    private bolum b;

    public randevu(Long id_r, String Tarih, hasta h, doktor d, bolum b) {
        this.id_r = id_r;
        this.Tarih = Tarih;
        this.h = h;
        this.d = d;
        this.b = b;
    }


    public randevu() {
    }

    public Long getId_r() {
        return id_r;
    }

    public void setId_r(Long id_r) {
        this.id_r = id_r;
    }

    public String getTarih() {
        return Tarih;
    }

    public void setTarih(String Tarih) {
        this.Tarih = Tarih;
    }

    public hasta getH() {
        return h;
    }

    public void setH(hasta h) {
        this.h = h;
    }

    public doktor getD() {
        return d;
    }

    public void setD(doktor d) {
        this.d = d;
    }

    public bolum getB() {
        return b;
    }

    public void setB(bolum b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "randevu{" + "id_r=" + id_r + ", Tarih=" + Tarih + ", h=" + h + ", d=" + d + ", b=" + b + '}';
    }

}
