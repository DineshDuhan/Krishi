package com.example1.android.krishimantra;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateInfo extends AppCompatActivity {
    String[] languages = { "Wheat","Consumer","Vegetable","Rice","Organic Manure"};
    EditText username, email, password,contact,crop,rate,city;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog dialog;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
       // ImageButton imageButton = (IButton)findViewById(R.id.farmerimage);
        imageView = (ImageView)findViewById(R.id.imageID);

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, languages);
        //Find TextView control
       // AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.languages);
        //Set the number of characters the user must type before the drop down list is shown
      //  acTextView.setThreshold(1);
        //Set the adapter
      //  acTextView.setAdapter(adapter);
        username = (EditText) findViewById(R.id.user);
         city =(EditText)findViewById(R.id.city);
        email = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.pass);
        crop =  (EditText) findViewById(R.id.crop);
        rate = (EditText) findViewById(R.id.rate);
        contact= (EditText) findViewById(R.id.contact);
        //
        auth = FirebaseAuth.getInstance();
    }
public void click(View View){
    Intent i = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    startActivityForResult(i, CAMERA_REQUEST);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

           // ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }





    public void updateinfo(View View){
        String text_username = username.getText().toString();
        String text_city = city.getText().toString();
        String text_crop  = crop.getText().toString();
        String text_rate = rate.getText().toString();
        String text_contact = contact.getText().toString();
        String uid = auth.getCurrentUser().getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("kisan").child(uid);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", text_username);
        hashMap.put("City", text_city);
        hashMap.put("Crop", text_crop);
        hashMap.put("Rate", text_rate);
        hashMap.put("Contact", text_contact);
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(UpdateInfo.this, "Updated", Toast.LENGTH_SHORT).show();
                  /*  Intent intent = new Intent(PostDepartment.this, ShowDepartment.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
*/

            }
        });
    }
}
