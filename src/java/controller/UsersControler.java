/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Casper
 */
import dao.UsersDAO;
import Util.SessionUtils;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@javax.enterprise.context.SessionScoped

public class UsersControler implements Serializable {

    private UsersDAO userDao;

    private static final long serialVersionUID = 1094801825228386363L;
    private String msg;
 
    @NotNull(message = "Name can't be empty")
    @Size(min = 5, max = 10, message = "Name must have 5 to 10 charecters!!!")
    private String username;
    @NotNull(message = "password can't be empty")
   
    @Size(min = 5, max = 10, message = "password must have 5 to 10 charecters!!!")
    private String password;
    @NotNull(message = "password can't be empty")
    private String email;
    
    
    public void create() {
       
        this.getUserDao().insert(username, password);
    }

    public UsersControler() {
    }

    public UsersControler(String msg, String uname, String password) {
        this.msg = msg;
        this.username = uname;
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsersDAO getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UsersDAO();
        }
        return userDao;
    }

    public void setUserDao(UsersDAO userDao) {
        this.userDao = userDao;
    }

    //validate login
    public String validateUsernamePasswordAdmin() {  //giriş doğrulamak için
        boolean valid = UsersDAO.validate(username, password);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return "login?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,  //hata mesajı
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "index?faces-redirect=true";
        }
    }

    private void clear()
    {
        username = "";
        password = "";
    };
    
   
}

