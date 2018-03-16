/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.queenmaryuniversity.musicrythm.views.songs;

import org.primefaces.model.LazyDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import uk.co.queenmaryuniversity.musicrythm.model.Song;

 
/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazySongDataModel extends LazyDataModel<Song> {
     
    private List<Song> datasource;
     
    public LazySongDataModel(List<Song> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Song getRowData(String rowKey) {
        for(Song song : datasource) {       
                return song;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Song song) {
        return song.getId();
    }
 
    @Override
    public List<Song> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Song> data = new ArrayList<>();
        data.addAll(datasource);
 
    
         //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}