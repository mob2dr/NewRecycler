package com.example.recycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddData extends AppCompatActivity {
    private EditText addName;
    private EditText addNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        initView();
        if (getIntent().hasExtra("data")) {
            Data contact = (Data) getIntent().getSerializableExtra("data");
            addName.setText(contact.getName());
             addNumber.setText(contact.getNumber());
        }
    }

    void initView() {
        addName = findViewById(R.id.add_name);
        addNumber=findViewById(R.id.add_number);

        FloatingActionButton fab = findViewById(R.id.floatb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = creatData(addName.getText().toString(), addNumber.getText().toString());
                if (data != null) {
                    Intent intent = new Intent();
                    intent.putExtra("data", data);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private Data creatData(String name, String number) {
        boolean invalid = false;
        if (name == null || name.isEmpty()) {
            invalid = true;
            addName.setError("ادخل الاسم من فضلك");
        }
        if (number == null || number.isEmpty()) {
            invalid = true;
            addNumber.setError("ادخل رقم من فضلك ");
        }
        if (invalid) return null;
        else return new Data(name, number);
    }
}
