package com.example.xiangmuu.net.api;

import java.util.HashMap;

public class HttpUrlConnetionUtils implements INetWork{

    private static  HttpUrlConnetionUtils httpUrlConnetionUtils;

    private HttpUrlConnetionUtils() {
    }

    public static HttpUrlConnetionUtils getInstance() {
        if(httpUrlConnetionUtils == null){
            synchronized (RetrofitUtils.class){
                if (httpUrlConnetionUtils == null){
                    httpUrlConnetionUtils = new HttpUrlConnetionUtils();
                }
            }
        }
        return httpUrlConnetionUtils;
    }

    @Override
    public <T> void get(String url,INetCallBack<T> netCallBack) {
    }

    @Override
    public <T> void get(String url, HashMap<String, String> s,INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url,INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> s,INetCallBack<T> netCallBack) {

    }
}
