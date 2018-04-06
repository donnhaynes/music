package uk.co.queenmaryuniversity.musicrythm.model;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ACE
 */
@Named(value = "signup")
@RequestScoped
public class SignUPManagedBean {
    @EJB
    private MusicRhythmDAO dao;
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
        user.setRegistrationDate(new Date());
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
