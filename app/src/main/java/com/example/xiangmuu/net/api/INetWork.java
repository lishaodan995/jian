package com.example.xiangmuu.net.api;

import java.util.HashMap;


public interface INetWork {


    <T> void get(String url, INetCallBack<T> netCallBack);
    <T> void get(String url, HashMap<String, String> s, INetCallBack<T> netCallBack);

    <T> void post(String url, INetCallBack<T> netCallBack);
    <T> void post(String url, HashMap<String, String> s, INetCallBack<T> netCallBack);



}
