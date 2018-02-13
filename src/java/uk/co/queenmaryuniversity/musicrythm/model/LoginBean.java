/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author ACE
 */
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable{
    private String username;
    private String password;
    private Boolean rememberMe;
    private Boolean isLoggedIn = false;
    private User user;

    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public void submit(){
        MusicRhythmDAO dao = new MusicRhythmDAO();
        this.user = dao.login(username, password);
        if (user == null){
            
        }else{
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
    
    
    
    
    
    
}
