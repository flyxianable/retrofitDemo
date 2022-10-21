
android retrofit 示例工程

使用api.aa1.cn的免费接口请求名人名言，每次自动获取一条
见：https://api.aa1.cn/doc/api-wenan-mingrenmingyan.html

retrofit请求步骤如下：
1、依赖包引入
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:3.12.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'

2、创建接口设置请求类型与参数，

见com.example.jetpack.retrofitdemo.retrofit.ApiService

   @GET("/api/api-wenan-mingrenmingyan/index.php")
    Call<ResponseBody> getFamousWordsTextResult(@Query("aa1") String form);

3、创建Retrofit对象、设置数据解析器。
Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(host).addConverterFactory(
                GsonConverterFactory.create(gson)).build();

4、生成接口对象
ApiService apiService = retrofit.create(ApiService.class);

5、调用接口方法返回Call对象
 final Call<ResponseBody> call = apiService.getFamousWordsTextResult(form);

 6、发送请求 （同步、异步）
（1）同步：调用Call对象的execute()，返回结果的响应体
（2）异步：调用Call对象的enqueue()，参数是一个回调

如果想直接获取到返回的json串，实体用ResponseBody，也可以用实体类，但如果json格式不规范，会造成解析失败
建议用ResponseBody，在onResponse里自行解析

 call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
    }
 }

7、对返回的数据进行解析

public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

}
