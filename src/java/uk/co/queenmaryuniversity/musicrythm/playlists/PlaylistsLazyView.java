/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.playlists;

import uk.co.queenmaryuniversity.musicrythm.views.playlists.*;
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
import uk.co.queenmaryuniversity.musicrythm.model.PlayList;
 
@ManagedBean(name="playlistsLazyView")
@ViewScoped
public class PlaylistsLazyView implements Serializable {             
    private LazyDataModel<Playlist> lazyModel;     
    private Playlist selectedPlaylist;
    
    
     
    @PostConstruct
    public void init() { 
        MusicRhythmDAO dao = new MusicRhythmDAO();
     //   List<Playlist> playlists = dao.findPlaylistsByQuery(searchQuery,bpm,range);
        lazyModel = new LazyPlaylistDataModel(playlists);
    }
 
    public LazyDataModel<Playlist> getLazyModel() {
        return lazyModel;
    }
 
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Playlist Selected", ((Playlist) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
    
}