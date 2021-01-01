/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Casper
 */
@Named
@SessionScoped
public class LoginController implements Serializable {
    private User user;

    public String login(){
        
        if( this.user.getUsername().equals("admin") && this.user.getPassword().equals("1234")) { //bu bilgileri girerse geçerli kullanıcı
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);
            return "/secret/secret?faces-redirect=true" ;
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hatali kullanici adi veya sifre"));
            return "/login.xhtml";
        }
    }
    
    public User getUser() {
        if (this.user == null)
            this.user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
