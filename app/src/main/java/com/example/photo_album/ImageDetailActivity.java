package com.example.photo_album;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.photo_album.utils.ApplicationConstant;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    TextView tv_title, tv_copyright, tv_date, tv_explain;
    ImageView iv_image, iv_back;
    LinearLayout ll_translate;
    String pos ="";
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        tv_title = findViewById(R.id.tv_title);
        tv_copyright = findViewById(R.id.tv_copyright);
        tv_date = findViewById(R.id.tv_date);
        tv_explain = findViewById(R.id.tv_explain);
        iv_image = findViewById(R.id.iv_image);
        ll_translate = findViewById(R.id.ll_translate);
        iv_back = findViewById(R.id.iv_back);

        pos = getIntent().getStringExtra("position");
        position = Integer.parseInt(pos);

        Picasso.get()
                .load(ApplicationConstant.sharedArray.get(position).getHdUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.err_placeholder)
                .fit()
                .noFade()
                .into(iv_image);

        tv_title.setText(ApplicationConstant.sharedArray.get(position).getTitle());
        tv_copyright.setText(ApplicationConstant.sharedArray.get(position).getCopyRight());
        tv_date.setText(ApplicationConstant.sharedArray.get(position).getDate());
        tv_explain.setText(ApplicationConstant.sharedArray.get(position).getExplanation());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ll_translate.animate().translationY(0);
            }
        }, 300);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}