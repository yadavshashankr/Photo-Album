package com.example.photo_album;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    //declaring necesary variables

    ImageView iv_obvious;
    TextView tv_shashank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv_obvious = findViewById(R.id.iv_obvious);
        tv_shashank = findViewById(R.id.tv_shashank);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_obvious.animate().translationY(0);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iv_obvious.animate().translationY(-2000);
                        tv_shashank.animate().translationY(0);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                               startActivity(intent);
                               finish();
                               overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

                            }
                        }, 1000);
                    }
                }, 2000);
            }
        }, 1500);


    }



}
