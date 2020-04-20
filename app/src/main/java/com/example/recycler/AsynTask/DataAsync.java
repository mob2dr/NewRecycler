package com.example.recycler.AsynTask;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.recycler.Data;
import com.example.recycler.Database.AppDatabase;

import java.util.List;

public class DataAsync extends AsyncTask<Void,Void,List<Data>> {
    AppDatabase db;
    private  CallBackData  callBackData;
    public interface CallBackData{
        void getCallBack(List<Data>Datas);
    }
    public DataAsync(@NonNull AppDatabase database , CallBackData callBackData){
        db=database;
        this.callBackData=callBackData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Data> doInBackground(Void... voids) {

        return db.dataDao().getDatas();
    }

    @Override
    protected void onPostExecute(List<Data> data) {
        super.onPostExecute(data);
        callBackData.getCallBack(data);
    }
}
