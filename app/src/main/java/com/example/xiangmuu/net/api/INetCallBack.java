package com.example.xiangmuu.net.api;

public interface INetCallBack<T> {
    void onSuccess(T t);
    void onError(Throwable throwable);
}
