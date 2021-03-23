package com.example.photo_album.adapters;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photo_album.ImageDetailActivity;
import com.example.photo_album.R;
import com.example.photo_album.holders.ImageHolder;
import com.example.photo_album.models.ImageModel;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {
    private Context mContext;
    public List<ImageModel> mImageDet = new ArrayList<>();




    public ImageAdapter(Context mContext, List<ImageModel> mImageDet) {
        this.mContext = mContext;
        this.mImageDet = mImageDet;

    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageHolder holder, final int position) {

        final int positionTrue= position;
        holder.setIsRecyclable(false);
        final ImageModel imageModel = mImageDet.get(position);


        if (imageModel.getUrl() != null && !imageModel.getUrl().isEmpty()){
            Picasso.get()
                    .load(imageModel.getUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.err_placeholder)
                    .fit()
                    .noFade()
                    .into(holder.iv_image);
        }

            holder.iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,holder.iv_image,"imageMain");
                    Intent i = new Intent(mContext, ImageDetailActivity.class);
                    i.putExtra("position", String.valueOf(position));
                    mContext.startActivity(i, activityOptionsCompat.toBundle());
                }
            });

    }



    @Override
    public int getItemCount() {
        return mImageDet.size();
    }
}