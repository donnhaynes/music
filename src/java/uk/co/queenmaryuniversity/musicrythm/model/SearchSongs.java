/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ACE
 */
@Named(value = "searchsongs")
@ConversationScoped
public class SearchSongs implements Serializable {

    @EJB
    private MusicRhythmDAO dao;
    private String searchQuery;
    private Integer bpm;
    private Integer range = 10;
    private List<Song> songs;

    /**
     * Creates a new instance of SearchSongs
     */
    public SearchSongs() {
    }

    public String submit() {
        this.songs = dao.findSongsByQuery(searchQuery, bpm, range);
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

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

}
