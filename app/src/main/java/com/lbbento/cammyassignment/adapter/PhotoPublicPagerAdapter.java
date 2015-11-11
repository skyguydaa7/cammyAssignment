package com.lbbento.cammyassignment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lbbento.cammyassignment.api.model.PhotoPublicItem;
import com.lbbento.cammyassignment.view.PhotoPublicItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbbento on 10/11/15.
 *
 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
 * representing an object in the collection.
 */

public class PhotoPublicPagerAdapter extends FragmentStatePagerAdapter {

    private List<PhotoPublicItem> mPhotoPublicItemList;
    FragmentManager oFragmentManager;
    ArrayList<Fragment> oPooledFragments;


    public PhotoPublicPagerAdapter(FragmentManager fragmentManager, List<PhotoPublicItem> pPhotoPublicItem) {
        super(fragmentManager);
        this.mPhotoPublicItemList = pPhotoPublicItem;
        oFragmentManager=fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        PhotoPublicItem mPhotoPublicItem = mPhotoPublicItemList.get(position);

        return (Fragment) PhotoPublicItemFragment.newInstance(mPhotoPublicItem);
    }

    @Override
    public int getCount() {
        return mPhotoPublicItemList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        Fragment oFragment = (Fragment) object;
        oPooledFragments = new ArrayList<>(oFragmentManager.getFragments());
        if (oPooledFragments.contains(oFragment))
            return POSITION_NONE;
        else
            return POSITION_UNCHANGED;
    }
}
