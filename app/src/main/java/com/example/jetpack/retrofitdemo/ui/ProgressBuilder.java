package com.example.jetpack.retrofitdemo.ui;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressBuilder {

    private ProgressDialog progressDialog;
    private Context mContext;


    public ProgressBuilder(Context context){
        mContext = context;
    }

    public void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage("数据加载中");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void cancelProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
