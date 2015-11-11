package com.lbbento.cammyassignment.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lbbento.cammyassignment.api.util.VolleyClient;

import de.greenrobot.event.EventBus;
import icepick.Icepick;

/**
 * Created by lbbento on 11/11/15.
 */
public class BaseFragment extends Fragment {

    /**
     * Save the instance and objects anotted with @Param using IcePick
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

}
