/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ACE
 */
@Named(value = "searchsongs")
@ConversationScoped
public class SearchSongs implements Serializable{
    private String searchQuery;
    private Double rythmn;
    private List<Song> songs;

    /**
     * Creates a new instance of SearchSongs
     */
    public SearchSongs() {
    }

    public String submit(){
        MusicRhythmDAO dao = new MusicRhythmDAO();
        this.songs = dao.findSongsByQuery(searchQuery);
        return "searchresults";
    }
    
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Double getRythmn() {
        return rythmn;
    }

    public void setRythmn(Double rythmn) {
        this.rythmn = rythmn;
    }
    
    
    
    
    
    
}
