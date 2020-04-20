package com.example.recycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.recycler.AsynTask.AddDataAsync;
import com.example.recycler.AsynTask.DataAsync;
import com.example.recycler.AsynTask.DelDataAsync;
import com.example.recycler.AsynTask.EditDataAsync;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import com.example.recycler.Database.AppDatabase;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final int ADD_REQUEST_CODE = 1;
    final int EDIT_REQUEST_CODE = 2;
    AppDatabase db ;

    DataAsync.CallBackData callBackData=new DataAsync.CallBackData() {
        @Override
        public void getCallBack(List<Data> Datas) {
             adapter.submitList(Datas);
        }
    };
    DataAdapter.OnDataClicked o = new DataAdapter.OnDataClicked() {
        @Override
        public void edit(Data data, int position) {
            Intent intent = new Intent(MainActivity.this, AddData.class);
            intent.putExtra("data", data);
            intent.putExtra("position", position);
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }

        @Override
        public void delete(Data data ) {
            DelDataAsync del=new DelDataAsync(db,callBackData);
            del.execute(data);
            }

        };

        DataAdapter adapter = new DataAdapter(o);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        db = AppDatabase.getInstance(this);
        DataAsync dataAsync=new DataAsync(db,callBackData);
        dataAsync.execute();

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
            AddDataAsync add = new AddDataAsync(db,callBackData);
            add.execute((Data) data.getSerializableExtra("data"));
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            EditDataAsync edit =new EditDataAsync(db,callBackData);
            edit.execute((Data) data.getSerializableExtra("data"));
        }


        }
    }



