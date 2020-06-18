package com.example1.android.krishimantra;


import android.content.Intent;
import android.support.annotation.NonNull;
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


public class customer extends AppCompatActivity {
    private RecyclerView mbloglist;
    DatabaseReference mref;
    List<String> list;
    ArrayList<String> myList;
    Query query;
    int flag=0;
    FirebaseRecyclerAdapter<blog,BlogViewHolder> firebaseRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        mbloglist = (RecyclerView)findViewById(R.id.RView);
        mbloglist.setHasFixedSize(true);
        mbloglist.setLayoutManager(new LinearLayoutManager(this));


    }
    public void search(String str){
        flag=1;
        query = FirebaseDatabase.getInstance().getReference().child("kisan")
                .orderByChild("City")
                .startAt(str).endAt( str + "\uf8ff");

        onStart();
    }
    @Override
    protected void onStart() {

        super.onStart();
        myList = new ArrayList<String>();

        if(flag==0){
            query = FirebaseDatabase.getInstance().getReference().child("kisan")
                    .orderByChild("City")
                    .startAt("").endAt("\uf8ff");

        }
        mref =  FirebaseDatabase.getInstance().getReference().child("kisan");
        FirebaseRecyclerOptions<blog> options =
                new FirebaseRecyclerOptions.Builder<blog>()
                        .setQuery(query, blog.class)
                        .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<blog, BlogViewHolder>(
                options
        ) {

            @Override
            protected void onBindViewHolder(@NonNull BlogViewHolder holder, int position, @NonNull blog model) {
                holder.setname(model.getName());
                holder.setcity(model.getCity());
                holder.setcrop(model.getCrop());
                holder.setrate(model.getRate());
                holder.setcontact(model.getContact());
            }

            @NonNull
            @Override
            public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.customer_row, viewGroup, false);
                return new BlogViewHolder(view);

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
        public void  setname(String name)
        {
            TextView cus_name = (TextView)mview.findViewById(R.id.name2);

            cus_name.setText("Name : "+ name);
        }
        public  void  setcity(String city){
            TextView cus_city = (TextView)mview.findViewById(R.id.city);

            cus_city.setText("City : "+city);
        }
        public  void  setcrop(String crop){
            TextView cus_crop = (TextView)mview.findViewById(R.id.crop);
            cus_crop.setText("Crop : "+crop);
        }
        public  void  setrate(String rate){
            TextView cus_rate = (TextView)mview.findViewById(R.id.rate);
            cus_rate.setText("Rate/100 kg : "+rate);
        }
        public  void  setcontact(String contact){
            TextView cus_contact = (TextView)mview.findViewById(R.id.contact);
            cus_contact.setText("Contact : "+contact);
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
