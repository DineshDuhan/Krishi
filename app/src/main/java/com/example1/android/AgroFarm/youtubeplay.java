package com.example1.android.AgroFarm;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example1.android.AgroFarm.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class youtubeplay extends YouTubeBaseActivity{
    YouTubePlayerView youTubePlayerView;
    Button button;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubeplay);





        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        button = (Button) findViewById(R.id.button);


        onInitializedListener = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("2ddw_rp3cMo");

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);
                Intent i = new Intent(youtubeplay.this,basic.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),basic.class));
        overridePendingTransition(0,0);

    }
}