package com.lbbento.cammyassignment;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by lbbento on 10/11/15.
 */
public class CammyAssignmentApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //Fresco - Facebook IMage manager
        Fresco.initialize(this);
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
