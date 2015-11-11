package com.lbbento.cammyassignment.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import icepick.Icepick;

/**
 * Created by lbbento on 10/11/15.
 */
public class BaseFragment extends Fragment {

    /**
     * Save the instance and objects with thr annotation @State using IcePick
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

}
