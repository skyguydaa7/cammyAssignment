package com.lbbento.cammyassignment.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lbbento on 10/11/15.
 */
public class PhotoPublicItem implements Serializable {

    private String title;
    private Date published;
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

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }
}

