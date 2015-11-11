package com.lbbento.cammyassignment.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lbbento.cammyassignment.R;
import com.lbbento.cammyassignment.api.model.PhotoPublicItem;
import com.lbbento.cammyassignment.view.base.BaseFragment;

import icepick.Icepick;
import icepick.State;

/**
 * Created by lbbento on 10/11/15.
 */

public class PhotoPublicItemFragment extends BaseFragment {

    private static final String PARAM_ITEM = "param_item";
    @State protected PhotoPublicItem mPhotoPublicItem;

    public static android.support.v4.app.Fragment newInstance(PhotoPublicItem mPhotoPublicItem) {

        PhotoPublicItemFragment f = new PhotoPublicItemFragment();

        // Supply category input as an argument.
        Bundle args = new Bundle();
        args.putSerializable(PARAM_ITEM, mPhotoPublicItem);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
        else {
            Bundle args = getArguments();
            if (args != null) {
                mPhotoPublicItem = ((PhotoPublicItem) args.getSerializable(PARAM_ITEM));
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_photo_item, container, false);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);
    }

    /**
     * Bind the views to the local objects.
     */
    private void bindViews(View view) {
        SimpleDraweeView mSimpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image);

        mSimpleDraweeView.setImageURI(Uri.parse(mPhotoPublicItem.getMedia().getM()));
    }

}
