/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author ACE
 */
@Named(value = "signup")
@Dependent
public class SignUPManagedBean {
    private String name;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    /**
     * Creates a new instance of SignUPManagedBean
     */
    public SignUPManagedBean() {
    }
    
    public void submit(){
        System.out.println("Data submitted:"+getName());
        MusicRhythmDAO dao = new MusicRhythmDAO();
       // dao.saveUser(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
}
