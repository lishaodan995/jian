package com.example.xiangmuu.base;

public class BasePresenter<T> {

   protected T mview;

    public void AttachView(T baseView){
        mview = baseView;
    }
    public void disAttachView(){
        mview = null;
    }
}
