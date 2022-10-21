package com.example.jetpack.retrofitdemo.retrofit;


import com.example.jetpack.retrofitdemo.entity.FamousWordsResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * step2:创建接口设置请求类型与参数
 * 名人名言
 * Retrofi
 */
public interface ApiService {

    @GET("/api/api-wenan-mingrenmingyan/index.php")
    Call<ResponseBody> getFamousWordsTextResult(@Query("aa1") String form);

    @GET("/api/api-wenan-mingrenmingyan/index.php")
    Call<FamousWordsResult> getFamousWordsJsonResult(@Query("aa1") String form);
}
