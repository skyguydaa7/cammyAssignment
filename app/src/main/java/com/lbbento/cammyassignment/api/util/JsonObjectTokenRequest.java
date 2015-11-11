package com.lbbento.cammyassignment.api.util;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by lbbento on 20/10/15.
 */

public class JsonObjectTokenRequest extends JsonObjectRequest{


    public JsonObjectTokenRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }



}
