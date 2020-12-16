/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.doktorDAO;
import entity.doktor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Casper
 */
@FacesConverter(value = "doktorconverter") //xhtml tarafında görünür olması için
public class doktorconverter implements Converter {

    private doktorDAO doktorDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) { //xhtml tarafından string olarak  gelen veriyi nesneye dönüştüren kod
        return this.getDoktorDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) { //nesneyi string return eder
        doktor d = (doktor) arg2;
        return d.getId_dok().toString();
    }

    public doktorDAO getDoktorDAO() {
        if (this.doktorDAO == null) {
            this.doktorDAO = new doktorDAO();
        }
        return doktorDAO;
    }
}
