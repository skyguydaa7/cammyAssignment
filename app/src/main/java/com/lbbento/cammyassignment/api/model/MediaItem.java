package com.lbbento.cammyassignment.api.model;

import java.io.Serializable;

/**
 * Created by lbbento on 10/11/15.
 */
public class MediaItem implements Serializable{

    private String m;

    public MediaItem(){};

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
