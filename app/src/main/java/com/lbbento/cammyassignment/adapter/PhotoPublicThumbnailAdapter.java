package com.lbbento.cammyassignment.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lbbento.cammyassignment.R;
import com.lbbento.cammyassignment.api.model.PhotoPublicItem;

import java.util.ArrayList;

/**
 * Created by lbbento on 10/11/15.
 */
public class PhotoPublicThumbnailAdapter extends  RecyclerView.Adapter <PhotoPublicThumbnailAdapter.ListItemViewHolder>   {

    private ArrayList<PhotoPublicItem> mPhotoPublicItemList;
    private Context ctx;
    private int itemSelected = 0;

    public PhotoPublicThumbnailAdapter(Context context, ArrayList<PhotoPublicItem> pPhotoPublicItemList) {
        ctx = context;
        this.mPhotoPublicItemList = pPhotoPublicItemList;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = null;

        switch (viewType) {
            case 0:
                itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.card_photo_item, viewGroup, false);
                break;
        }
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder viewHolder, int position) {
        PhotoPublicItem mPhotoPublicItem = mPhotoPublicItemList.get(position);

        viewHolder.imageThumbnail.setImageURI(Uri.parse(mPhotoPublicItem.getMedia().getM()));

        if (position == itemSelected)
            viewHolder.cardView.setBackgroundColor(ctx.getResources().getColor(R.color.thumbnail_item_selected));
        else
            viewHolder.cardView.setBackgroundColor(ctx.getResources().getColor(android.R.color.transparent));

    }

    @Override
    public int getItemCount() {
        return mPhotoPublicItemList.size();
    }

    public final class ListItemViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageThumbnail;
        CardView cardView;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            imageThumbnail = (SimpleDraweeView) itemView.findViewById(R.id.imageCard);
            cardView = (CardView) itemView;
        }



    }

    public Object getItem(int position) {
        return mPhotoPublicItemList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * Set thumbnail item selected - highlighted
     * @param position
     */

    public void setItemSelected(int position) {
        itemSelected = position;
    }

}
