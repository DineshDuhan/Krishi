package com.example1.android.AgroFarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example1.android.AgroFarm.R;

public class farmerdeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmerdeal);
        setTitle("AgroFarm");
    }


    public void makepaymenttofarmer(View view) {
        Intent i = new Intent(farmerdeal.this,paymentcustomer.class);
        startActivity(i);
    }
}
