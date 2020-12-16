/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.bolumDAO;
import entity.bolum;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Casper
 */
@FacesConverter(value = "bolumconverter")
public class bolumconverter implements Converter {

    private bolumDAO bolumDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBolumDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        bolum b = (bolum) arg2;
        return b.getId_bolum().toString();
    }

    public bolumDAO getBolumDAO() {
        if (this.bolumDAO == null) {
            this.bolumDAO = new bolumDAO();
        }
        return bolumDAO;
    }

}

