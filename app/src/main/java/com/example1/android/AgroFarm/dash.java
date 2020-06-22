 package com.example1.android.AgroFarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example1.android.AgroFarm.R;

 public class dash extends AppCompatActivity {
     private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        setTitle("User_Name");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_dashboard);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    startActivity(new Intent(getApplicationContext(),basic.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_dashboard:


                    return true;
                case R.id.navigation_notifications:

                    startActivity(new Intent(getApplicationContext(),about.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };



     @Override
     public void onBackPressed() {
         startActivity(new Intent(getApplicationContext(),basic.class));
         overridePendingTransition(0,0);

     }


     public void carpool(View view) {
         Intent i = new Intent(dash.this,carpooling.class);
         startActivity(i);
     }

     public void customerpay(View view) {
        Intent i = new Intent(dash.this,customer.class);
         startActivity(i);
     }

     public void farmersInLocation(View view) {
         Intent i = new Intent(dash.this,equip.class);
         startActivity(i);
     }

 }

