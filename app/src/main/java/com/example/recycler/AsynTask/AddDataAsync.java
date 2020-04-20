package com.example.recycler.AsynTask;

import android.os.AsyncTask;

import com.example.recycler.Data;
import com.example.recycler.Database.AppDatabase;

import java.util.List;

public class AddDataAsync extends AsyncTask<Data,Void, List<Data>> {

    private AppDatabase db;
    private DataAsync.CallBackData callBackData;
    public AddDataAsync(AppDatabase database , DataAsync.CallBackData callBackData){
        db=database;
        this.callBackData=callBackData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Data> doInBackground(Data... datas) {
        for (Data d: datas){
             db.dataDao().insertData(d);
        }
        return db.dataDao().getDatas();
    }

    @Override
    protected void onPostExecute(List<Data> data) {
        super.onPostExecute(data);
        callBackData.getCallBack(data);
    }
}
