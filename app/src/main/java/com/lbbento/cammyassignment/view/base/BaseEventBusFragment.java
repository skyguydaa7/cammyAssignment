package com.lbbento.cammyassignment.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lbbento.cammyassignment.api.util.VolleyClient;

import de.greenrobot.event.EventBus;
import icepick.Icepick;

/**
 * Created by lbbento on 11/11/15.
 */
public class BaseEventBusFragment extends Fragment {

    protected String tag;
    protected EventBus eventBus;

    /**
     * Save the instance and objects anotted with @Param using IcePick
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    /**
     * Cancell all threads and Unregister the view, therefore it won't listen for eventBus events
     */
    @Override
    public void onDetach() {
        VolleyClient.getInstance(getContext()).cancelAll(tag);
        eventBus.unregister(this);
        super.onDetach();
    }

}
