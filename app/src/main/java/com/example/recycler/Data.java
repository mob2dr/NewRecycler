package com.example.recycler;


import android.database.MergeCursor;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

@Entity(tableName = "MyData")
public class Data implements Serializable {
    @ColumnInfo(name = "Name")
    private String name;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Number")
    private String number;
public Data(String name, String number) {
        this.name = name;
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
