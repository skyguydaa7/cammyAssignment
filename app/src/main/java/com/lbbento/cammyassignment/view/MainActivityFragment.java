package com.lbbento.cammyassignment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lbbento.cammyassignment.R;
import com.lbbento.cammyassignment.adapter.PhotoPublicPagerAdapter;
import com.lbbento.cammyassignment.adapter.PhotoPublicThumbnailAdapter;
import com.lbbento.cammyassignment.api.control.ApiPhotoPublicControl;
import com.lbbento.cammyassignment.api.model.PhotoPublic;
import com.lbbento.cammyassignment.api.model.PhotoPublicItem;
import com.lbbento.cammyassignment.view.animation.DepthPageTransformer;
import com.lbbento.cammyassignment.view.base.BaseEventBusFragment;
import com.lbbento.cammyassignment.view.util.ItemClickSupport;
import com.lbbento.cammyassignment.view.util.ViewUtil;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import icepick.Icepick;
import icepick.State;

/**
 * by Lucas - 10/11/15
 * A placeholder fragment containing the main view = gallery.
 */
public class MainActivityFragment extends BaseEventBusFragment {

    //Adapters
    private PhotoPublicPagerAdapter mPhotoPublicPagerAdapter;
    private PhotoPublicThumbnailAdapter mPhotoPublicThumbnailAdapter;

    //WIdgets
    private RecyclerView mRecyclerImageThumbnail;
    private ViewPager imageViewPager;
    private ImageView refreshIcon;
    private TextView mTextError;

    private Menu menu;

    @State  protected ArrayList<PhotoPublicItem> mPhotoPublicItemList;


    public MainActivityFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
        else {
            mPhotoPublicItemList = new ArrayList<>();
        }

        //create adapters
        mPhotoPublicPagerAdapter = new PhotoPublicPagerAdapter(getChildFragmentManager(), mPhotoPublicItemList);
        mPhotoPublicThumbnailAdapter = new PhotoPublicThumbnailAdapter(getContext(), mPhotoPublicItemList);


        //Eventbus
        tag = "MainFrag";
        eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this))
            eventBus.register(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);
        setUpToolbar(view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_refresh);
        item.setActionView(R.layout.refresh_icon);
        refreshIcon = (ImageView) item.getActionView().findViewById(R.id.refreshButton);

        refreshIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        this.menu = menu;
    }

    /**
     * Bind the views to the local objects.
     */
    private void bindViews(View view) {

        mTextError = (TextView) view.findViewById(R.id.error);

        //IMage view
        imageViewPager = (ViewPager) view.findViewById(R.id.imageViewPager);
        imageViewPager.setPageTransformer(true, new DepthPageTransformer());
        imageViewPager.setAdapter(mPhotoPublicPagerAdapter);
        imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPhotoPublicThumbnailAdapter.setItemSelected(position);
                mRecyclerImageThumbnail.scrollToPosition(position);
                mPhotoPublicThumbnailAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //thumbnail list view
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerImageThumbnail = (RecyclerView) view.findViewById(R.id.imageThumbnails);
        mRecyclerImageThumbnail.setLayoutManager(layoutManager);
        mRecyclerImageThumbnail.setAdapter(mPhotoPublicThumbnailAdapter);
        ItemClickSupport.addTo(mRecyclerImageThumbnail).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                imageViewPager.setCurrentItem(position, true);
            }
        });


        //get data
        if (mPhotoPublicItemList == null || mPhotoPublicItemList.isEmpty())
            getData();
    }

    private void setUpToolbar(View view) {
        try {
            Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

            // Set the padding to match the Status Bar height
            mToolbar.setPadding(0, ViewUtil.getStatusBarHeight(getContext()), 0, 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get data from the API
     */
    private void getData() {
        ApiPhotoPublicControl.getPhotoPublic(getContext(), tag);
        startRefreshing();

    }

    /**
     * EventBus Listener - To receive the object from the API - Do not call this method
     */
    public void onEvent(PhotoPublic mPhotoPublic) {
        if (mPhotoPublic != null) {
            try {
                mTextError.setVisibility(View.GONE);
                //clear
                mPhotoPublicItemList.clear();
                mPhotoPublicPagerAdapter = new PhotoPublicPagerAdapter(getChildFragmentManager(), mPhotoPublicItemList);
                imageViewPager.setAdapter(mPhotoPublicPagerAdapter);

                //new items
                mPhotoPublicItemList.addAll(mPhotoPublic.getItems());

                //refresh
                mPhotoPublicPagerAdapter.notifyDataSetChanged();
                mPhotoPublicThumbnailAdapter.notifyDataSetChanged();
                mRecyclerImageThumbnail.scrollToPosition(0);
                mPhotoPublicThumbnailAdapter.setItemSelected(0);
                imageViewPager.setCurrentItem(0);

                stopRefreshing();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * EventBus Listener - In case of error - DO not call this method
     */
    public void onEvent(VolleyError error) {
        //error
        stopRefreshing();

        mPhotoPublicItemList.clear();
        mPhotoPublicPagerAdapter.notifyDataSetChanged();
        mPhotoPublicThumbnailAdapter.notifyDataSetChanged();

        mTextError.setVisibility(View.VISIBLE);
    }

    /**
     * Start the refreshing Icon animation
     */
    private void startRefreshing() {
        if (refreshIcon != null) {
            // animation start
            Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
            rotation.setRepeatCount(Animation.INFINITE);
            refreshIcon.startAnimation(rotation);
    }
    }

    /**
     * Stop the refreshing Icon animation
     */
    private void stopRefreshing()
    {
        if (refreshIcon != null)
        refreshIcon.clearAnimation();
    }


}
