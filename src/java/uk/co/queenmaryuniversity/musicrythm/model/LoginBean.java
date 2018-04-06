/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author ACE
 */
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable{
    @EJB
    private MusicRhythmDAO dao;
    private String username;
    private String password;
    private Boolean rememberMe;
    private Boolean isLoggedIn = false;
    private User user;
    private UIComponent signInbutton;

    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public void submit(){
        this.user = dao.login(username, password);        
        if (user == null){
            System.out.println("invalid credentials");
            // invalid
            FacesMessage message = new FacesMessage("Invalid credentials");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(signInbutton.getClientId(context), message);        
        }else{
            System.out.println("login succesful");
            isLoggedIn = true;
        }
    }
    
    public void logout(){
        user = null;
        isLoggedIn = false;
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

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UIComponent getSignInbutton() {
        return signInbutton;
    }

    public void setSignInbutton(UIComponent signInbutton) {
        this.signInbutton = signInbutton;
    }
    
    
    
    
}
