package com.example1.android.krishimantra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updatecustomer extends AppCompatActivity {
    EditText username,contact,capacity,vehicle,rate,city;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatecustomer);
        username = (EditText) findViewById(R.id.user1);
        city =(EditText)findViewById(R.id.city1);
       // email = (EditText) findViewById(R.id.mail);
      //  password = (EditText) findViewById(R.id.pass);
        vehicle = (EditText)findViewById(R.id.vehicle1);
        capacity =  (EditText) findViewById(R.id.capacity1);
        rate = (EditText) findViewById(R.id.rate);
        contact= (EditText) findViewById(R.id.contact);
        //
        auth = FirebaseAuth.getInstance();
    }


    public void updateCusinfo(View View){
        String text_username = username.getText().toString();
        String text_city = city.getText().toString();
        String text_capacity  = capacity.getText().toString();
        String text_rate = rate.getText().toString();
        String text_contact = contact.getText().toString();
        String text_vehicle = vehicle.getText().toString();
        String uid = auth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("customer").child(uid);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", text_username);
        hashMap.put("City", text_city);
        hashMap.put("Capacity", text_capacity);
        hashMap.put("Vehicle",text_vehicle);
        hashMap.put("Rate", text_rate);
        hashMap.put("Contact", text_contact);
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(updatecustomer.this, "Updated", Toast.LENGTH_SHORT).show();
                  /*  Intent intent = new Intent(PostDepartment.this, ShowDepartment.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
*/

            }
        });
    }
}
