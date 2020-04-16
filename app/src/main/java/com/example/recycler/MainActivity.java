package com.example.recycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.app.ActivityCompat.startActivityForResult;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final int ADD_REQUEST_CODE = 1;
    final int EDIT_REQUEST_CODE = 2;
    AppDatabase db ;
    List<Data> dbContacts;
    DataAdapter.OnDataClicked o = new DataAdapter.OnDataClicked() {
        @Override
        public void edit(Data data, int position) {
            Intent intent = new Intent(MainActivity.this, AddData.class);
            intent.putExtra("data", data);
            intent.putExtra("position", dbContacts.indexOf(data));
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }

        @Override
        public void delete(Data data ) {
            db.dataDao().delete(data);
            dbContacts=db.dataDao().getDatas();
            ArrayList<Data> list = new ArrayList<>(dbContacts);
            adapter.submitList(list);
            }

        };

        DataAdapter adapter = new DataAdapter(o);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        db = AppDatabase.getInstance(this);
        dbContacts = db.dataDao().getDatas();
        adapter.submitList(dbContacts);
    }

    void initViews() {
        recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.floatb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddData.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            db.dataDao().insertData((Data) data.getSerializableExtra("data"));
            dbContacts=db.dataDao().getDatas();
            ArrayList<Data> list=new ArrayList<Data>(dbContacts);
            adapter.submitList(list);
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            db.dataDao().updataData((Data) data.getSerializableExtra("data"));
            dbContacts=db.dataDao().getDatas();
            ArrayList<Data> list=new ArrayList<Data>(dbContacts);
            adapter.submitList(list);

        }


        }
    }



