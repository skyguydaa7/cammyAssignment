package com.lbbento.cammyassignment.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbbento on 10/11/15.
 */
public class PhotoPublic implements Serializable {

    private String title;
    private String link;
    private ArrayList<PhotoPublicItem> items = new ArrayList<>();


    public PhotoPublic(){}



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<PhotoPublicItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PhotoPublicItem> items) {
        this.items = items;
    }
}
