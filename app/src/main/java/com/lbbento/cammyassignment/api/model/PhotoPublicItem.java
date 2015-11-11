package com.lbbento.cammyassignment.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbbento on 10/11/15.
 */
public class PhotoPublicItem implements Serializable {

    private String title;
    private MediaItem media;

    public PhotoPublicItem() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaItem getMedia() {
        return media;
    }

    public void setMedia(MediaItem media) {
        this.media = media;
    }
}

