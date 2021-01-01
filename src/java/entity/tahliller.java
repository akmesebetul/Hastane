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
public class tahliller {

    private Long id_tahlil;
    private String tipi;
    private hasta h;
    private doktor d;
    

    public tahliller(Long id_tahlil, String tipi, hasta h, doktor d) {
        this.id_tahlil = id_tahlil;
        this.tipi = tipi;
        this.h = h;
        this.d = d;
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

    

    public tahliller() {
    }

    public Long getId_tahlil() {
        return id_tahlil;
    }

    public void setId_tahlil(Long id_tahlil) {
        this.id_tahlil = id_tahlil;
    }


    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    @Override
    public String toString() {
        return "tahliller{" + "id_tahlil=" + id_tahlil + ", tipi=" + tipi + ", h=" + h + ", d=" + d + '}';
    }

}
