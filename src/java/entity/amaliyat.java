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
public class amaliyat {

    private long id_ameliyat;
    private String ameliyat_ismi;
    private String ameliyat_tarihi;
    private hasta hasta;
    private doktor doktor;

    public long getId_ameliyat() {
        return id_ameliyat;
    }

    public void setId_ameliyat(long id_ameliyat) {
        this.id_ameliyat = id_ameliyat;
    }

    public String getAmeliyat_ismi() {
        return ameliyat_ismi;
    }

    public void setAmeliyat_ismi(String ameliyat_ismi) {
        this.ameliyat_ismi = ameliyat_ismi;
    }

    public String getAmeliyat_tarihi() {
        return ameliyat_tarihi;
    }

    public void setAmeliyat_tarihi(String ameliyat_tarihi) {
        this.ameliyat_tarihi = ameliyat_tarihi;
    }

    public hasta getHasta() {
        return hasta;
    }

    public void setHasta(hasta hasta) {
        this.hasta = hasta;
    }

    public doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(doktor doktor) {
        this.doktor = doktor;
    }

    public amaliyat() {
    }

    public amaliyat(long id_ameliyat, String ameliyat_ismi, String ameliyat_tarihi, hasta hasta, doktor doktor) {
        this.id_ameliyat = id_ameliyat;
        this.ameliyat_ismi = ameliyat_ismi;
        this.ameliyat_tarihi = ameliyat_tarihi;
        this.hasta = hasta;
        this.doktor = doktor;
    }

    @Override
    public String toString() {
        return "amaliyat{" + "id_ameliyat=" + id_ameliyat + ", ameliyat_ismi=" + ameliyat_ismi + ", ameliyat_tarihi=" + ameliyat_tarihi + ", hasta=" + hasta + ", doktor=" + doktor + '}';
    }

}
