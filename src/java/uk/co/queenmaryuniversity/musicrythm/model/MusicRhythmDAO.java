/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.model;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Don
 */
public class MusicRhythmDAO {

    private EntityManager em;

    public MusicRhythmDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicRythmPU");
        em = emf.createEntityManager();
        //dfsjkasdjkl
    }

    public void saveUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void savePlayList(PlayList playlist) {
        // save the playlist in the database
    }

    public User login(String username, String password) {
        try {
            Query query = em.createQuery("Select u from User u where u.username = :username OR u.email = :username AND u.password = :password ");
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

    public List<Song> findSongsByQuery(String searchQuery, Integer bpm) {
         List<Song> songs = findSongsByName(searchQuery);
         if (bpm > 0){
             return songs.stream().filter(song -> ((bpm-10 < song.getRhythm()) && bpm+10 > song.getRhythm())).collect(Collectors.toList()); 
         }
         return songs;        
    }
}
