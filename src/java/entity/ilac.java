/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author Casper
 */
public class ilac {
    private Long id_ilac ;
    private String ilac_adi ;
    private doktor doktor;
    private hasta hasta;

    public ilac(Long id_ilac, String ilac_adi, doktor doktor, hasta hasta) {
        this.id_ilac = id_ilac;
        this.ilac_adi = ilac_adi;
        this.doktor = doktor;
        this.hasta = hasta;
    }

    public ilac() {
    }
    

    public Long getId_ilac() {
        return id_ilac;
    }

    public void setId_ilac(Long id_ilac) {
        this.id_ilac = id_ilac;
    }

    public String getIlac_adi() {
        return ilac_adi;
    }

    public void setIlac_adi(String ilac_adi) {
        this.ilac_adi = ilac_adi;
    }

    public doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(doktor doktor) {
        this.doktor = doktor;
    }

    public hasta getHasta() {
        return hasta;
    }

    public void setHasta(hasta hasta) {
        this.hasta = hasta;
    }

    @Override
    public String toString() {
        return "ilac{" + "id_ilac=" + id_ilac + ", ilac_adi=" + ilac_adi + ", doktor=" + doktor + ", hasta=" + hasta + '}';
    }
    
    
    
    

  

}
