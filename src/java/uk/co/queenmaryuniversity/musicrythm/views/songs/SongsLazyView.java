/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.views.songs;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import uk.co.queenmaryuniversity.musicrythm.model.LoginBean;
import uk.co.queenmaryuniversity.musicrythm.model.MusicRhythmDAO;
import uk.co.queenmaryuniversity.musicrythm.model.PlayList;
import uk.co.queenmaryuniversity.musicrythm.model.SearchSongs;
import uk.co.queenmaryuniversity.musicrythm.model.Song;
import uk.co.queenmaryuniversity.musicrythm.model.User;

@ManagedBean(name = "songsLazyView")
@ViewScoped
public class SongsLazyView implements Serializable {

    @EJB
    private MusicRhythmDAO dao;
    @ManagedProperty(value = "#{searchsongs}")
    private SearchSongs searchSongs;

    @ManagedProperty(value = "#{login}")
    private LoginBean loginBean;
    private LazyDataModel<Song> lazyModel;
    private Song songToBeAddedToPlaylist;
    private String searchQuery;
    private Integer bpm;
    private Integer range = 10;
    private String playlistId;
    private String newPlaylistName;

    @PostConstruct
    public void init() {
        this.searchQuery = searchSongs.getSearchQuery();
        this.bpm = searchSongs.getBpm();
        this.range = searchSongs.getRange();
        List<Song> songs = dao.findSongsByQuery(searchQuery, bpm, range);
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

    public void addToPlaylist(Song song) {
        System.out.println("button clicked:" + song.getName());
        setSongToBeAddedToPlaylist(song);
    }

    public Song getSongToBeAddedToPlaylist() {
        return songToBeAddedToPlaylist;
    }

    public void setSongToBeAddedToPlaylist(Song songToBeAddedToPlaylist) {
        this.songToBeAddedToPlaylist = songToBeAddedToPlaylist;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getNewPlaylistName() {
        return newPlaylistName;
    }

    public void setNewPlaylistName(String newPlaylistName) {
        this.newPlaylistName = newPlaylistName;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void submitAddToPlaylist() {
        System.out.println("submitAddToPlaylist");
        if (this.newPlaylistName != null && !this.newPlaylistName.trim().isEmpty()) {
            PlayList newPlayList = new PlayList();
            newPlayList.setName(this.newPlaylistName);
            newPlayList.getSongs().add(this.songToBeAddedToPlaylist);
            final User user = loginBean.getUser();
            newPlayList.setUser(user);
            dao.savePlayList(newPlayList);
            user.getPlaylists().add(newPlayList);
            this.newPlaylistName = null;
            this.playlistId = null;
            this.songToBeAddedToPlaylist = null;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Song added to playlist"));
        } else {
            System.out.println("playlist:" + playlistId);
            PlayList selectedPlaylist = null;
            for (PlayList playlist : loginBean.getUser().getPlaylists()) {
                if (("" + playlist.getId()).equals(this.playlistId)) {
                    selectedPlaylist = playlist;
                }
            }
            selectedPlaylist.getSongs().add(songToBeAddedToPlaylist);
            dao.updatePlaylist(selectedPlaylist);
            this.newPlaylistName = null;
            this.playlistId = null;
            this.songToBeAddedToPlaylist = null;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Song added to playlist"));
        }
    }

}
