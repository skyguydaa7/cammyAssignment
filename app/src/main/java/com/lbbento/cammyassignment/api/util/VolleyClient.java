package com.lbbento.cammyassignment.api.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by lbbento on 10/11/15.
 */
public class VolleyClient {

    private static VolleyClient webRequestQueue;
    private RequestQueue volleyRequestQueue;

    private VolleyClient(Context context) {
        volleyRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static VolleyClient getInstance(Context context) {
        if (webRequestQueue == null) {
            synchronized (VolleyClient.class) {
                if (webRequestQueue == null) {
                    webRequestQueue = new VolleyClient(context);
                }
            }
        }
        return webRequestQueue;
    }

    public void addToQueue(Request request, String tag) {
        request.setTag(tag);
        volleyRequestQueue.add(request);
    }

    public void cancelAll(String tag) {
        volleyRequestQueue.cancelAll(tag);
    }
}