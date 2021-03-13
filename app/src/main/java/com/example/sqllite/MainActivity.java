package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycleView;
    FloatingActionButton add_button;
    DatabaseHelper muDB;
    ArrayList<String> id,name,address, email,contact;
    CustomArrayAdapter customArrayAdapter;
    SwipeRefreshLayout swipe_refresh;
    public static final int OPEN_NEW_ACTIVITY = 12345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = findViewById(R.id.recycle_view);
        add_button = findViewById(R.id.add_button);
        swipe_refresh = findViewById(R.id.swipe_refresh);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, AddressAddActivity.class);
//                intent.putExtras(storeDataInArray());
                startActivityForResult(intent,OPEN_NEW_ACTIVITY);
            }
        });

        muDB = new DatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        email = new ArrayList<>();
        address = new ArrayList<>();
        contact = new ArrayList<>();
        storeDataInArray();
        customArrayAdapter = new CustomArrayAdapter(MainActivity.this, id, name,address,email,contact);
        recycleView.setAdapter(customArrayAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        swipe_refresh.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                swipe_refresh.setRefreshing(false);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_NEW_ACTIVITY) {
            // Execute your code on back here
            // ....
             Intent intent = getIntent();
             finish();
             startActivity(intent);
        }
    }
    void storeDataInArray(){
        Cursor cursor = muDB.readAllData();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No Data :-(", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                address.add(cursor.getString(2));
                email.add(cursor.getString(3));
                contact.add(cursor.getString(4));
            }
        }
    }
}