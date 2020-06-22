package com.example1.android.AgroFarm;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example1.android.AgroFarm.R;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView img = (ImageView)findViewById(R.id.img1);
        setTitle("AgroFarm");
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                img.animate().alpha(0f).setDuration(3000);
                handler.postDelayed(this,4000);


            }
        };
        handler.post(run);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, youtubeplay.class);
                startActivity(intent);
            }
        }, 4000); // 4 seconds



    }

}
