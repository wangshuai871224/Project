package com.example.dllo.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private String url = "https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up";

    private String imgUrl = "http://img.hb.aicdn.com/8b62c46e11798607ac172fdaedf488a72d18daee1cfc4-j9dfIJ_fw658";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.image);

        // Volley的使用分为三步
        // 第一步: 创建请求队列
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        // 第二步: 创建请求
        GsonRequest<TestBean> gsonRequest = new GsonRequest<TestBean>(TestBean.class,
                url, new Response.Listener<TestBean>() {
            @Override
            public void onResponse(TestBean response) {
                // 请求成功的方法
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

//        StringRequest stringRequest = new StringRequest(url,
//                new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    // 请求成功
//                    Log.d("MainActivity", response);
//                    // 解析
//                    Gson gson = new Gson();
//                    // 参数一: 想要解析的字符串
//                    // 参数二: 想要解析成什么样的
//                    TestBean bean = gson.fromJson(response, TestBean.class);
//
//                }
//            },  new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // 请求失败
//                    Log.d("MainActivity", error.getMessage());
//                }
//        });

        // 第三步: 把请求放到请求队列里
        VolleySingleton.getInstance().addRequest(gsonRequest);

        // 请求图片
        VolleySingleton.getInstance().getImage(imgUrl, imageView);
//        ImageLoader imageLoader = new ImageLoader(VolleySingleton.getInstance().getRequestQueue()
//                                                 , new MemoryCache());
//        imageLoader.get(imgurl, ImageLoader.getImageListener(imageView,
//                R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }
}
