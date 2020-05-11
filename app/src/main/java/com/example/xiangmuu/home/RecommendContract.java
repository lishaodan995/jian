package com.example.xiangmuu.home;

import com.example.xiangmuu.net.api.INetCallBack;

public class RecommendContract {

    public interface IRecommendView {
        void  setRecommendList(NewsBean string);
        void setColumList(ColunmBean columList);
    }
    public interface IRecommendMode{
        <T> void getRecommendList(String id, INetCallBack<T> iNetCallBack);
        <T> void getColumList(INetCallBack<T> iNetCallBack);
    }
    public interface IRecommendPresenter {
        void getColumList();
        void getRecommendList(String id);
    }


}
