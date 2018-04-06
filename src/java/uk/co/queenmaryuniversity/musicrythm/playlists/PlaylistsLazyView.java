/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.playlists;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import uk.co.queenmaryuniversity.musicrythm.model.LoginBean;
import uk.co.queenmaryuniversity.musicrythm.model.MusicRhythmDAO;
import uk.co.queenmaryuniversity.musicrythm.model.PlayList;
 
@ManagedBean(name="playlistsLazyView")
@ViewScoped
public class PlaylistsLazyView implements Serializable {     
    @EJB
    private MusicRhythmDAO dao;
    private LazyDataModel<PlayList> lazyModel;     
    private PlayList selectedPlaylist;
    @ManagedProperty(value="#{login}")
    private LoginBean  loginBean; 
    
     
    @PostConstruct
    public void init() { 
        final List<PlayList> playlists = loginBean.getUser().getPlaylists();        
        lazyModel = new LazyPlaylistDataModel(playlists);
    }
 
    public LazyDataModel<PlayList> getLazyModel() {
        return lazyModel;
    }
 
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Playlist Selected", ""+((PlayList) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }       

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public PlayList getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(PlayList selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }
    
    
    public void deletePlaylist(PlayList playlist) {
        System.out.println("user playlist:"+loginBean.getUser().getPlaylists().size());
        dao.deletePlaylist(playlist);   
        loginBean.setUser(dao.findUserById(loginBean.getUser().getId()));        
        System.out.println("user playlist after:"+loginBean.getUser().getPlaylists().size());
        ((LazyPlaylistDataModel)lazyModel).setDatasource(loginBean.getUser().getPlaylists());
        
    }
    
    
    
    
}