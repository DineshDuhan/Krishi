package com.example1.android.krishimantra;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Far_Equip extends AppCompatActivity {
    private RecyclerView mbloglist;
    DatabaseReference mref;
    List<String> list;
    ArrayList<String> myList;
    Query query;
    int flag=0;
    LocationManager locationManager;
    LocationListener locationListener;
    FirebaseRecyclerAdapter<blog1, equip.BlogViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_far__equip);
        mbloglist = (RecyclerView)findViewById(R.id.RView2);
        mbloglist.setHasFixedSize(true);
        mbloglist.setLayoutManager(new LinearLayoutManager(this));

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(Far_Equip.this, "Location"+location.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }
    public void search(String str){
        flag=1;
        query = FirebaseDatabase.getInstance().getReference().child("Equipments")
                .orderByChild("Equipment")
                .startAt(str).endAt( str + "\uf8ff");

        onStart();
    }
    @Override
    protected void onStart() {

        super.onStart();
        myList = new ArrayList<String>();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(Far_Equip.this, "Location"+location.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


        if(flag==0){
            query = FirebaseDatabase.getInstance().getReference().child("Equipment")
                    .orderByChild("Equipment")
                    .startAt("").endAt("\uf8ff");

        }
        //  mref =  FirebaseDatabase.getInstance().getReference().child("customer");
        FirebaseRecyclerOptions<blog1> options =
                new FirebaseRecyclerOptions.Builder<blog1>()
                        .setQuery(query, blog1.class)
                        .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<blog1, equip.BlogViewHolder>(
                options
        ){

            @Override
            protected void onBindViewHolder(@NonNull equip.BlogViewHolder holder, int position, @NonNull blog1 model) {
                holder.setname1(model.getName());
                holder.setcity1(model.getCity());

                holder.setrate1(model.getRate());
                holder.setcontact1(model.getContact());
                holder.setvehicle1(model.getVehicle());
                holder.setcapac(model.getCapacity());
            }

            @NonNull
            @Override
            public equip.BlogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.shared_row, viewGroup, false);
                return new equip.BlogViewHolder(view);
            }
        };
        mbloglist.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
        }
        public void  setname1(String name)
        {
            TextView cus_name = (TextView)mview.findViewById(R.id.name1);
            cus_name.setText("Name : "+ name);
        }

        public  void  setcity1(String city){
            TextView cus_city = (TextView)mview.findViewById(R.id.city2);

            cus_city.setText(String.format("City : %s", city));
        }

        public  void  setcapac(String cap){
            TextView cus_cap = (TextView)mview.findViewById(R.id.capacity2);

            cus_cap.setText(String.format("Capacity in hours : %s", cap));
        }
        public  void  setrate1(String rate){
            TextView cus_rate = (TextView)mview.findViewById(R.id.rate1);
            cus_rate.setText("Rate/hour : "+rate);
        }
        public  void  setcontact1(String contact){
            TextView cus_contact = (TextView)mview.findViewById(R.id.contact1);
            cus_contact.setText("Contact : "+contact);
        }
        public  void  setvehicle1(String Equipment){
            TextView cus_vehicle = (TextView)mview.findViewById(R.id.vehicle1);
            cus_vehicle.setText("Equipment : "+Equipment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.search_item);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);


    }


}
