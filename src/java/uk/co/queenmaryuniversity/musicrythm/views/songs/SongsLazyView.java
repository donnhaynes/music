/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.views.songs;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import uk.co.queenmaryuniversity.musicrythm.model.MusicRhythmDAO;
import uk.co.queenmaryuniversity.musicrythm.model.SearchSongs;
import uk.co.queenmaryuniversity.musicrythm.model.Song;
 
@ManagedBean(name="songsLazyView")
@ViewScoped
public class SongsLazyView implements Serializable {     
    @ManagedProperty(value="#{searchsongs}")
    private SearchSongs  searchSongs;
    private LazyDataModel<Song> lazyModel;     
    private Song selectedSong;
    private String searchQuery;
    private Integer bpm;
    private Integer range=10;     
    
     
    @PostConstruct
    public void init() { 
        this.searchQuery=searchSongs.getSearchQuery();
        this.bpm=searchSongs.getBpm();
        this.range=searchSongs.getRange();
        MusicRhythmDAO dao = new MusicRhythmDAO();
        List<Song> songs = dao.findSongsByQuery(searchQuery,bpm,range);
        lazyModel = new LazySongDataModel(songs);
    }
 
    public LazyDataModel<Song> getLazyModel() {
        return lazyModel;
    }
 
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Song Selected", ((Song) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public SearchSongs getSearchSongs() {
        return searchSongs;
    }

    public void setSearchSongs(SearchSongs searchSongs) {
        this.searchSongs = searchSongs;
    }
    
    
}