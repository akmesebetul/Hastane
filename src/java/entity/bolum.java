/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Casper
 */
public class bolum {
private Long id_bolum ;
private String bolum_ismi ;

    public bolum(Long id_bolum, String bolum_ismi) {
        this.id_bolum = id_bolum;
        this.bolum_ismi = bolum_ismi;
    }


    public bolum() {
    }

    public Long getId_bolum() {
        return id_bolum;
    }

    public void setId_bolum(Long id_bolum) {
        this.id_bolum = id_bolum;
    }


    public String getBolum_ismi() {
        return bolum_ismi;
    }

    public void setBolum_ismi(String bolum_ismi) {
        this.bolum_ismi = bolum_ismi;
    }

    @Override
    public String toString() {
        return "bolum{" + "id_bolum=" + id_bolum + ", bolum_ismi=" + bolum_ismi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id_bolum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final bolum other = (bolum) obj;
        if (!Objects.equals(this.id_bolum, other.id_bolum)) {
            return false;
        }
        return true;
    }

  
 
}
