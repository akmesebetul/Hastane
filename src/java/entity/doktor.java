/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Casper
 */
public class doktor {

    private Long id_dok;
    private String isim;
    private String t_c;
    private String tel_numarasi;
    private List<hasta> hastalar;

    public doktor(Long id_dok, String isim, String t_c, String tel_numarasi, List<hasta> hastalar) {
        this.id_dok = id_dok;
        this.isim = isim;
        this.t_c = t_c;
        this.tel_numarasi = tel_numarasi;
        this.hastalar = hastalar;
    }

    public doktor() {
    }

    public Long getId_dok() {
        return id_dok;
    }

    public void setId_dok(Long id_dok) {
        this.id_dok = id_dok;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getT_c() {
        return t_c;
    }

    public void setT_c(String t_c) {
        this.t_c = t_c;
    }

    public String getTel_numarasi() {
        return tel_numarasi;
    }

    public void setTel_numarasi(String tel_numarasi) {
        this.tel_numarasi = tel_numarasi;
    }

    public List<hasta> getHastalar() {
        return hastalar;
    }

    public void setHastalar(List<hasta> hastalar) {
        this.hastalar = hastalar;
    }

    @Override
    public String toString() {
        return "doktor{" + "id_dok=" + id_dok + ", isim=" + isim + ", t_c=" + t_c + ", tel_numarasi=" + tel_numarasi + ", hastalar=" + hastalar + '}';
    }

    @Override
    public int hashCode() { //bir nesnenin hangi gruba sınıflandırılması gerektiğine karar vermek için hashCode
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id_dok);
        return hash;
    }

    @Override
    public boolean equals(Object obj) { //İlgili metot, iki nesnenin içeriğinin aynı olup olmadığını kontrol eder.
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final doktor other = (doktor) obj;
        if (!Objects.equals(this.id_dok, other.id_dok)) {
            return false;
        }
        return true;
    }

}
