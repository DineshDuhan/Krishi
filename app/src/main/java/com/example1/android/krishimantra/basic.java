package com.example1.android.krishimantra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class basic extends AppCompatActivity {
    private TextView mTextMessage;
    FirebaseUser firebaseUser;
    private String admin_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        setTitle("Home");
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    return true;
                case R.id.navigation_dashboard:

                    startActivity(new Intent(getApplicationContext(),dash.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_notifications:

                    startActivity(new Intent(getApplicationContext(),about.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };




    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            finishAffinity();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void youtube(View view) {
        Intent i = new Intent(basic.this,youtubeplay.class);
        startActivity(i);
    }

    public void logto(View view) {
        if (firebaseUser != null) {
            Toast.makeText(basic.this, "You are already logged In", Toast.LENGTH_SHORT).show();
        }
        else {

            Intent i = new Intent(basic.this, login.class);
            startActivity(i);
        }
    }

    public void registerto(View view) {
        Intent i = new Intent(basic.this,register.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(basic.this,MainActivity.class));
                finish();
                return true;
        }
        return false;
    }

    public void update(View view) {
        Intent i = new Intent(basic.this,UpdateInfo.class);
        startActivity(i);
    }

    public void updatecustomer(View view) {
        Intent i = new Intent(basic.this,updatecustomer.class);
        startActivity(i);
    }
}
