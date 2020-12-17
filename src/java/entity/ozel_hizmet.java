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
public class ozel_hizmet {
    private long id_hizmet;
    private String hizmet_adi;
    private hasta hasta;

    public long getId_hizmet() {
        return id_hizmet;
    }

    public void setId_hizmet(long id_hizmet) {
        this.id_hizmet = id_hizmet;
    }

    public String getHizmet_adi() {
        return hizmet_adi;
    }

    public void setHizmet_adi(String hizmet_adi) {
        this.hizmet_adi = hizmet_adi;
    }

    public hasta getHasta() {
        return hasta;
    }

    public void setHasta(hasta hasta) {
        this.hasta = hasta;
    }

    public ozel_hizmet() {
    }

    public ozel_hizmet(long id_hizmet, String hizmet_adi, hasta hasta) {
        this.id_hizmet = id_hizmet;
        this.hizmet_adi = hizmet_adi;
        this.hasta = hasta;
    }

    @Override
    public String toString() {
        return "ozel_hizmet{" + "id_hizmet=" + id_hizmet + ", hizmet_adi=" + hizmet_adi + ", hasta=" + hasta + '}';
    }

  
    
    
    
}
