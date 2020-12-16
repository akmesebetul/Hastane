/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.hastaDAO;
import entity.hasta;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Casper
 */
@FacesConverter(value = "hastaconverter")
public class hastaconverter implements Converter {

    private hastaDAO hastaDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getHastaDAO().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        hasta h = (hasta) arg2;
        return h.getId_hasta().toString();
    }

    public hastaDAO getHastaDAO() {
        if (this.hastaDAO == null) {
            this.hastaDAO = new hastaDAO();
        }
        return hastaDAO;
    }

}
