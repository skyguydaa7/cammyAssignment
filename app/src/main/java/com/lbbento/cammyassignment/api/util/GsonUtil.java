package com.lbbento.cammyassignment.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lbbento.cammyassignment.api.model.PhotoPublic;

/**
 * Created by lbbento on 10/11/15.
 */
public class GsonUtil {


    /**
     * Convert the json to a Jva object using reflection.
     */


    public static PhotoPublic loadPhotoPublicFromJSONGson(String jsonString) {
        Gson gson = new GsonBuilder().create();
        PhotoPublic mPhotoPublic = gson.fromJson(jsonString, PhotoPublic.class);
        return mPhotoPublic;
    }





}

