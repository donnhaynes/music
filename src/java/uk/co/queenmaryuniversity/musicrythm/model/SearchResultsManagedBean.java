/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ACE
 */
@Named(value = "searchresults")
@RequestScoped
public class SearchResultsManagedBean {
    private List<Song> songs;
    /**
     * Creates a new instance of SearchResultsManagedBean
     */
    public SearchResultsManagedBean() {
        
    }
    
    
    @PostConstruct
    public void init() {
        MusicRhythmDAO dao = new MusicRhythmDAO();
        songs = dao.retrieveSongs();
    }

    public List<Song> getSongs() {
        return songs;
    }
    
    
    
}
