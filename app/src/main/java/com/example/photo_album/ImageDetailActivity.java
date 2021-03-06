package com.example.photo_album;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photo_album.utils.ApplicationConstant;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.squareup.picasso.Picasso;

public class ImageDetailActivity extends AppCompatActivity {

    TextView tv_title, tv_copyright, tv_date, tv_explain;
    ImageView iv_image, iv_back, iv_swip_left, iv_swip_left_2, iv_swip_right, iv_swip_right_2;
    LinearLayout ll_translate;
    RelativeLayout rl_left, rl_right;
    String pos ="";
    int position;
    NestedScrollView ns_swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        tv_title = findViewById(R.id.tv_title);
        rl_left = findViewById(R.id.rl_left);
        rl_right = findViewById(R.id.rl_right);
        tv_copyright = findViewById(R.id.tv_copyright);
        tv_date = findViewById(R.id.tv_date);
        tv_explain = findViewById(R.id.tv_explain);
        iv_image = findViewById(R.id.iv_image);
        ll_translate = findViewById(R.id.ll_translate);
        iv_back = findViewById(R.id.iv_back);
        ns_swipe = findViewById(R.id.ns_swipe);
        iv_swip_left = findViewById(R.id.iv_swip_left);
        iv_swip_left_2 = findViewById(R.id.iv_swip_left_2);
        iv_swip_right = findViewById(R.id.iv_swip_right);
        iv_swip_right_2 = findViewById(R.id.iv_swip_right_2);

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



        ns_swipe.setOnTouchListener(new OnSwipeTouchListener(ImageDetailActivity.this){


            public void onSwipeRight() {
                if (position > 0){
                    Intent i = new Intent(ImageDetailActivity.this, ImageDetailActivity.class);
                    i.putExtra("position", String.valueOf(--position));
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                }

            }
            public void onSwipeLeft() {
                if (position < (ApplicationConstant.sharedArray.size() - 1)){
                    Intent i = new Intent(ImageDetailActivity.this, ImageDetailActivity.class);
                    i.putExtra("position", String.valueOf(++position));
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                }

            }


        });


        rl_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position > 0){
                    Intent i = new Intent(ImageDetailActivity.this, ImageDetailActivity.class);
                    i.putExtra("position", String.valueOf(--position));
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                }
            }
        });

        rl_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < (ApplicationConstant.sharedArray.size() - 1)){
                    Intent i = new Intent(ImageDetailActivity.this, ImageDetailActivity.class);
                    i.putExtra("position", String.valueOf(++position));
                    startActivity(i);
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                }
            }
        });


        repeat();

    }

    private void fadeIn(View view){

    }

    private void primary(){

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(400);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(400);

        AnimationSet animation = new AnimationSet(false); //change to false
        AnimationSet animation2 = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation2.addAnimation(fadeOut);


        iv_swip_left_2.setAnimation(animation2);
        iv_swip_right_2.setAnimation(animation2);
        iv_swip_left.setAnimation(animation);
        iv_swip_right.setAnimation(animation);


//        iv_swip_left_2.setVisibility(View.INVISIBLE);
//        iv_swip_right_2.setVisibility(View.INVISIBLE);
//
//        iv_swip_left.setVisibility(View.VISIBLE);
//        iv_swip_right.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                secondary();

            }
        }, 700);
    }

    private void secondary(){

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);

        AnimationSet animation = new AnimationSet(false); //change to false
        AnimationSet animation2 = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation2.addAnimation(fadeOut);


        iv_swip_left_2.setAnimation(animation);
        iv_swip_right_2.setAnimation(animation);
        iv_swip_left.setAnimation(animation2);
        iv_swip_right.setAnimation(animation2);

//
//        iv_swip_left_2.setVisibility(View.VISIBLE);
//        iv_swip_right_2.setVisibility(View.VISIBLE);
//
//        iv_swip_left.setVisibility(View.INVISIBLE);
//        iv_swip_right.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                primary();

            }
        }, 700);
    }

    private void repeat(){
        primary();

    }


    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }
}