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
public class room {
    private long id_oda;
    private String kat;
    private int oda_numarasi;
    private hasta hasta;

    public long getId_oda() {
        return id_oda;
    }

    public void setId_oda(long id_oda) {
        this.id_oda = id_oda;
    }

    public String getKat() {
        return kat;
    }

    public void setKat(String kat) {
        this.kat = kat;
    }

    public int getOda_numarasi() {
        return oda_numarasi;
    }

    public void setOda_numarasi(int oda_numarasi) {
        this.oda_numarasi = oda_numarasi;
    }

    public hasta getHasta() {
        return hasta;
    }

    public void setHasta(hasta hasta) {
        this.hasta = hasta;
    }

    public room() {
    }

    public room(long id_oda, String kat, int oda_numarasi, hasta hasta) {
        this.id_oda = id_oda;
        this.kat = kat;
        this.oda_numarasi = oda_numarasi;
        this.hasta = hasta;
    }

    @Override
    public String toString() {
        return "room{" + "id_oda=" + id_oda + ", kat=" + kat + ", oda_numarasi=" + oda_numarasi + ", hasta=" + hasta + '}';
    }

   
    
    
    
}
