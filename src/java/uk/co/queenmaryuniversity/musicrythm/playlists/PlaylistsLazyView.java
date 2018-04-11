/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.playlists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import uk.co.queenmaryuniversity.musicrythm.model.LoginBean;
import uk.co.queenmaryuniversity.musicrythm.model.MusicRhythmDAO;
import uk.co.queenmaryuniversity.musicrythm.model.PlayList;
import uk.co.queenmaryuniversity.musicrythm.model.Song;
import uk.co.queenmaryuniversity.musicrythm.model.User;

@ManagedBean(name = "playlistsLazyView")
@ViewScoped
public class PlaylistsLazyView implements Serializable {

    @EJB
    private MusicRhythmDAO dao;
    private LazyDataModel<PlayList> lazyModel;
    private LazyDataModel<PlayList> playlistSongsLazyModel;
    private PlayList selectedPlaylist;    
    @ManagedProperty(value = "#{login}")
    private LoginBean loginBean;

    @PostConstruct
    public void init() {
        User user = loginBean.getUser();
        lazyModel = new LazyPlaylistDataModel(dao.retrieveUserPlaylists(user));
    }

    public LazyDataModel<PlayList> getLazyModel() {
        return lazyModel;
    }

    public LazyDataModel<Song> getPlaylistSongsLazyModel() {
        return new LazyDataModel<Song>() {
            @Override
            public Song getRowData(String rowKey) {
                for (Song song : selectedPlaylist.getSongs()) {
                    if (song.getId().equals(Long.parseLong(rowKey))) {
                        return song;
                    }
                }
                return null;
            }

            @Override
            public Object getRowKey(Song song) {
                return song.getId();
            }

            @Override
            public List<Song> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Song> data = new ArrayList<>();
                if (selectedPlaylist != null) {
                    data.addAll(selectedPlaylist.getSongs());
                }
                //rowCount
                int dataSize = data.size();
                this.setRowCount(dataSize);

                //paginate
                if (dataSize > pageSize) {
                    try {
                        return data.subList(first, first + pageSize);
                    } catch (IndexOutOfBoundsException e) {
                        return data.subList(first, first + (dataSize % pageSize));
                    }
                } else {
                    return data;
                }
            }

        };
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Playlist Selected", "" + ((PlayList) event.getObject()).getId());
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
        System.out.println("user playlist:" + loginBean.getUser().getPlaylists().size());
        dao.deletePlaylist(playlist);
        final List<PlayList> playlistsDatasource = ((LazyPlaylistDataModel) lazyModel).getDatasource();
        System.out.println("playlists #:" + playlistsDatasource.size());
        playlistsDatasource.remove(playlist);
        System.out.println("playlists #:" + playlistsDatasource.size());
    }

    public void deleteSong(Song song){
        selectedPlaylist.getSongs().remove(song);
        System.out.println("song removed");
    }
    
}
