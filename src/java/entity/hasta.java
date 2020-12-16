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
public class hasta {

    private Long id_hasta;
    private String isim;
    private String t_c;
    private String d_tarihi;
    private String tel_numarasi;
    private List<doktor> doktorlar;

    public hasta(Long id_hasta, String isim, String t_c, String d_tarihi, String tel_numarasi, List<doktor> doktorlar) {
        this.id_hasta = id_hasta;
        this.isim = isim;
        this.t_c = t_c;
        this.d_tarihi = d_tarihi;
        this.tel_numarasi = tel_numarasi;
        this.doktorlar = doktorlar;
    }

    public hasta() {
    }

    public Long getId_hasta() {
        return id_hasta;
    }

    public void setId_hasta(Long id_hasta) {
        this.id_hasta = id_hasta;
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

    public String getD_tarihi() {
        return d_tarihi;
    }

    public void setD_tarihi(String d_tarihi) {
        this.d_tarihi = d_tarihi;
    }

    public String getTel_numarasi() {
        return tel_numarasi;
    }

    public void setTel_numarasi(String tel_numarasi) {
        this.tel_numarasi = tel_numarasi;
    }

    public List<doktor> getDoktorlar() {
        return doktorlar;
    }

    public void setDoktorlar(List<doktor> doktorlar) {
        this.doktorlar = doktorlar;
    }

    @Override
    public String toString() {
        return "hasta{" + "id_hasta=" + id_hasta + ", isim=" + isim + ", t_c=" + t_c + ", d_tarihi=" + d_tarihi + ", tel_numarasi=" + tel_numarasi + ", doktorlar=" + doktorlar + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id_hasta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { //referansları eşitse aynı nesnedir
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {  //sınıfları farklıysa eşit değildir
            return false;
        }
        final hasta other = (hasta) obj;
        if (!Objects.equals(this.id_hasta, other.id_hasta)) {
            return false;
        }
        return true;
    }

}
