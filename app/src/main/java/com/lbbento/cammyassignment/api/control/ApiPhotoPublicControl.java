package com.lbbento.cammyassignment.api.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lbbento.cammyassignment.api.model.PhotoPublic;
import com.lbbento.cammyassignment.api.util.GsonUtil;
import com.lbbento.cammyassignment.api.util.JsonObjectTokenRequest;
import com.lbbento.cammyassignment.api.util.VolleyClient;

import org.json.JSONObject;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by lbbento on 10/11/15.
 */
public class ApiPhotoPublicControl {

    /**
     * Send request and get response using Volley from FLickr API, send it to the view using EventBus.
     * @param ctx Caller COntext
     * @param tag Tag to name the threads
     */
    public static void getPhotoPublic(Context ctx, String tag) {
        final EventBus eventBus = EventBus.getDefault();


        //Sending request & Getting response
        try {
            //post
            String url = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1";


            JsonObjectTokenRequest mJsonObjectTokenRequest = new JsonObjectTokenRequest(
                    Request.Method.GET, url,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject json) {
                            try {

                                PhotoPublic mPhotoPublic = GsonUtil.loadPhotoPublicFromJSONGson(json.toString());

                                eventBus.post(mPhotoPublic);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            eventBus.post(error);
                        }
                    });

            VolleyClient.getInstance(ctx).addToQueue(mJsonObjectTokenRequest, tag);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
