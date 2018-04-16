/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ACE
 */
@ManagedBean
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable {
    private Date lastCalled=new Date();

    @EJB
    private MusicRhythmDAO dao;
    private String username;
    private String password;
    private Boolean rememberMe;
    private User user;
    private UIComponent signInbutton;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        System.out.println("created----------------------");
        System.out.println("created----------------------");
        System.out.println("created----------------------");
        System.out.println("created----------------------");
        System.out.println("created----------------------");
        System.out.println("created----------------------");
        System.out.println("created----------------------");
        System.out.println("created----------------------");
    }

    public void submit() {
        System.out.println("SUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMITSUBMIT");
        User userLogin = dao.login(username, password);
        if (userLogin == null) {
            FacesMessage message = new FacesMessage("Invalid credentials");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(signInbutton.getClientId(context), message);
        } else {
            this.user=userLogin;
            RequestContext.getCurrentInstance().addCallbackParam("loggedIn", true);
            lastCalled=new Date();
        }
    }

    public boolean logout() {
        System.out.println("LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT_LOGOUT");
        if ((new Date().getTime()-lastCalled.getTime()) > 1000){
            setUser(null);
        }        
        return true;
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

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        System.out.println("User set  /////////////////");
        System.out.println("//////////////////////");
        this.user = user;
    }

    public UIComponent getSignInbutton() {
        return signInbutton;
    }

    public void setSignInbutton(UIComponent signInbutton) {
        this.signInbutton = signInbutton;
    }
    
    public boolean isLoggedIn(){
        /*System.out.println(" logged In:"+(user != null));
        System.out.println("*******************");*/
        return user != null;
    }

}
