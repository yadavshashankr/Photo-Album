package com.example.photo_album;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.photo_album.adapters.ImageAdapter;
import com.example.photo_album.models.ImageModel;
import com.example.photo_album.utils.ApplicationConstant;
import com.example.photo_album.utils.PlayGifView;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;



public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ImageAdapter imageAdapter;
    ArrayList<ImageModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_images);
        PlayGifView pGif = (PlayGifView) findViewById(R.id.gifvw_univ);

        pGif.setImageResource(R.drawable.univ_gif);
        //Test



        loadImages();

    }

    private void loadImages() {
        try {

            arrayList = new ArrayList<>();
            arrayList.clear();
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset());

            for (int i = 0 ; i < m_jArry.length() ; i++){
                JSONObject obj = m_jArry.getJSONObject(i);
                ImageModel imageModel = new ImageModel();
                if (!obj.isNull("copyright")){
                    imageModel.setCopyRight(obj.getString("copyright"));
                }else{
                    imageModel.setCopyRight("Anonymous");
                }
                imageModel.setDate(obj.getString("date"));
                imageModel.setExplanation(obj.getString("explanation"));
                imageModel.setHdUrl(obj.getString("hdurl"));
                imageModel.setMediaType(obj.getString("media_type"));
                imageModel.setServiceVersion(obj.getString("service_version"));
                imageModel.setTitle(obj.getString("title"));
                imageModel.setUrl(obj.getString("url"));
                arrayList.add(imageModel);

            }
            ApplicationConstant.sharedArray.clear();
            ApplicationConstant.sharedArray = arrayList;
            imageAdapter = new ImageAdapter(MainActivity.this, arrayList);
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(imageAdapter);
            imageAdapter.notifyDataSetChanged();

            mRecyclerView.setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = MainActivity.this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }


    @Override
    public void onBackPressed() {


            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit Application ?")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            /*if(HomePageViewerAdapter.newsList.size()!=0) {
                                HomePageViewerAdapter.newsList.clear();
                            }*/ //Added by Sagar
                            startActivity(intent);
                            finishAffinity();

                        }
                    }).setNegativeButton("No", null).show();

    }
}