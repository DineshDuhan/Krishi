package com.example1.android.krishimantra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class farmerdeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmerdeal);
    }


    public void makepaymenttofarmer(View view) {
        Intent i = new Intent(farmerdeal.this,paymentcustomer.class);
        startActivity(i);
    }
}
