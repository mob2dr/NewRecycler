package com.example.recycler.AsynTask;

import android.os.AsyncTask;

import com.example.recycler.Data;
import com.example.recycler.Database.AppDatabase;

import java.util.List;

public class DelDataAsync extends AsyncTask<Data,Void, List<Data>> {
    private AppDatabase db;
    private  DataAsync.CallBackData callBackData;
    public DelDataAsync(AppDatabase db, DataAsync.CallBackData callBackData){
        this.db=db;
        this.callBackData=callBackData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Data> doInBackground(Data... data) {
        for (Data d :data){
            db.dataDao().delete(d);
        }

        return db.dataDao().getDatas();
    }

    @Override
    protected void onPostExecute(List<Data> data) {
        super.onPostExecute(data);
        callBackData.getCallBack(data);
    }
}
