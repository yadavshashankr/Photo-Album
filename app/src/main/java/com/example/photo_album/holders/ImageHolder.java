package com.example.photo_album.holders;

import android.media.Image;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.photo_album.R;



public class ImageHolder extends RecyclerView.ViewHolder {

    public ImageView iv_image;
    public TextView tv_copy;


    public ImageHolder(View itemView) {
        super(itemView);
        iv_image = itemView.findViewById(R.id.iv_image);
        tv_copy = itemView.findViewById(R.id.tv_copy);

    }
}
