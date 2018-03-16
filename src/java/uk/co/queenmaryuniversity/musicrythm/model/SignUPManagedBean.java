package uk.co.queenmaryuniversity.musicrythm.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ACE
 */
@Named(value = "signup")
@RequestScoped
public class SignUPManagedBean {

    private User user;
    private String confirmPassword;

    /**
     * Creates a new instance of SignUPManagedBean
     */
    public SignUPManagedBean() {
    }

    @PostConstruct
    public void init() {
        user = new User();
    }

    public String submit() {
        MusicRhythmDAO dao = new MusicRhythmDAO();
        dao.saveUser(user);        
        return "signupsuccesful";
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public User getUser() {
        return user;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
