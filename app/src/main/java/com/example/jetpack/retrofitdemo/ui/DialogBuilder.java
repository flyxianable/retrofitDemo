package com.example.jetpack.retrofitdemo.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.example.jetpack.retrofitdemo.retrofit.RetrofitUtils;


public class DialogBuilder {

    RetrofitUtils mRetrofitUtils;

    public DialogBuilder(RetrofitUtils retrofitUtils){
        mRetrofitUtils = retrofitUtils;
    }


    public  void showDialog(Context context, Handler handler, ProgressBuilder progressBuilder, String content){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("名人名言");
        builder.setMessage(content);
        builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("换一个", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(progressBuilder != null) {
                    progressBuilder.showProgressDialog();
                }
                if(mRetrofitUtils != null) {
                    mRetrofitUtils.requestFamousWords("json");
                }
            }
        });
        builder.show();
    }

}
