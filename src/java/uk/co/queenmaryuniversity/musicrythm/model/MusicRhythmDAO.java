/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Don
 */
@Named
@Stateless
public class MusicRhythmDAO {
    @PersistenceContext(unitName = "MusicRhythmPU")
    private EntityManager em;
    
    public void saveUser(User user) {        
        em.persist(user);
        em.flush();
    }

    public void savePlayList(PlayList playlist) {
        em.persist(playlist);      
        em.flush();
    }  

    public User login(String username, String password) {
        try {
            Query query = em.createQuery("Select u from User u where (u.username = :username OR u.email = :username) AND u.password = :password ");
            query.setParameter("username", username);
            query.setParameter("password", password);
            return (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Song> retrieveSongs() {
        Query query = em.createQuery("Select s from Song s ");
        return query.getResultList();
    }

    public List<Song> findSongsByName(String name) {
        Query query = em.createQuery("Select s from Song s where s.name LIKE :songName");
        query.setParameter("songName", "%" + name + "%");
        return query.getResultList();
    }

    public static void main(String[] args) {
        MusicRhythmDAO dao = new MusicRhythmDAO();
        List<Song> songs = dao.findSongsByName("table");
        for (Song song : songs) {
            System.out.println("song:" + song.getName());
        }
        User user = new User();
        user.setName("James");
        dao.saveUser(user);

    }

    public List<Song> findSongsByQuery(String searchQuery) {
        return findSongsByName(searchQuery);
    }

    public List<Song> findSongsByQuery(String searchQuery, Integer bpm,Integer range) {
         List<Song> songs = findSongsByName(searchQuery);
         if (bpm != null && bpm > 0){
             return songs.stream().filter(song -> ((bpm-range < song.getBpm()) && bpm+range > song.getBpm())).collect(Collectors.toList()); 
         }
         return songs;        
    }

    public void updatePlaylist(PlayList playlist) {
        em.merge(playlist);
    }
    
    
    public void refreshUser(User user) {        
        em.refresh(user);
   
    }

    public void deletePlaylist(PlayList playList) {
        em.remove(em.find(PlayList.class, playList.getId()));
    }

    public User findUserById(Long id) {
        User user=em.find(User.class,id);
        em.refresh(user);
        return user;
    }
}
