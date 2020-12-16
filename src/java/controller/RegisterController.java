/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import dao.RegisterDao;

/**
 *
 * @author Casper
 */
@Named
@javax.enterprise.context.SessionScoped

public class RegisterController implements Serializable {

    private String userName;
    private String password;
    private RegisterDao registerDao;

    public String create() {

        this.getRegisterDao().insert(this.userName, this.password);
        this.userName = null;
        this.password = null;

        return "login";
    }

    public RegisterController() {
        this.userName = "";
        this.password = "";

    }

    public RegisterController(String userName, String password) {

        this.userName = userName;
        this.password = password;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterDao getRegisterDao() {
        return registerDao;
    }
}
