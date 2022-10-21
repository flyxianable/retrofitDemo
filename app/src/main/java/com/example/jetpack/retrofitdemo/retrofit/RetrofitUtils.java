package com.example.jetpack.retrofitdemo.retrofit;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    //名人名言使用免费api ：https://api.aa1.cn/doc/api-wenan-mingrenmingyan.html
    private final String host = "https://v.api.aa1.cn";
    private Handler mHandler;

    public RetrofitUtils(Handler handler){
        mHandler = handler;
    }

    public void requestFamousWords(String form){
        //Step3：创建Retrofit对象、设置数据解析器。
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(host).addConverterFactory(
                GsonConverterFactory.create(gson)).build();
        //Step4:生成接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        call(apiService, form);
        //Step5：调用接口方法返回Call对象


    }

    private void call(ApiService apiService, String form){
        final Call<ResponseBody> call = apiService.getFamousWordsTextResult(form);
        //Step6：发送请求(同步、异步)。
        //同步：调用Call对象的execute()，返回结果的响应体
        //异步：调用Call对象的enqueue()，参数是一个回调
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result ="";
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.v("liangyanbin", "text result = " + result);
                if(form.equals("json")){
                    JSONArray jsonArray = JSON.parseArray(result);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String words = jsonObject.getString("mingrenmingyan");
                    Log.v("liangyanbin", "words = " + words);
                    Message message = new Message();
                    message.what = 0;
                    message.obj = words;
                    mHandler.sendMessage(message);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("liangyanbin", "text Throwable =" + t.toString());
            }
        });
    }

/*    private void callJson(ApiService apiService){
        final Call<FamousWordsResult> call = apiService.getFamousWordsJsonResult("json");
        call.enqueue(new Callback<FamousWordsResult>() {
            @Override
            public void onResponse(Call<FamousWordsResult> call, Response<FamousWordsResult> response) {
                String mingrenmingyan = response.body().getMingrenmingyan();
                Log.v("liangyanbin", "json result =" + mingrenmingyan);
            }

            @Override
            public void onFailure(Call<FamousWordsResult> call, Throwable t) {
                Log.v("liangyanbin", "json Throwable =" + t.toString());
            }
        });
    }*/


}






