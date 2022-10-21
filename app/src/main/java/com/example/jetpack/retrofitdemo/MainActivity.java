package com.example.jetpack.retrofitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.jetpack.retrofitdemo.retrofit.RetrofitUtils;
import com.example.jetpack.retrofitdemo.ui.DialogBuilder;
import com.example.jetpack.retrofitdemo.ui.ProgressBuilder;

public class MainActivity extends AppCompatActivity {

    private ProgressBuilder progressBuilder;
    RetrofitUtils retrofitUtils;
    DialogBuilder mDialogBuilder;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                if(progressBuilder != null) {
                    progressBuilder.cancelProgressDialog();
                }
                String result = (String) msg.obj;
//                Toast.makeText(MainActivity.this, "the result is : " + result, Toast.LENGTH_LONG).show();
                mDialogBuilder.showDialog(MainActivity.this, this,  progressBuilder, result);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitUtils = new RetrofitUtils(handler);
        mDialogBuilder = new DialogBuilder(retrofitUtils);
        progressBuilder = new ProgressBuilder(this);
        findViewById(R.id.btnGo).setOnClickListener(view -> {
            if(progressBuilder != null) {
                progressBuilder.showProgressDialog();
            }
            new Thread(){
                @Override
                public void run() {
                    super.run();

                    retrofitUtils.requestFamousWords("json");
                }
            }.start();
        });
    }
}