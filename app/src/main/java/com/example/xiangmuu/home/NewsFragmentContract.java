package com.example.xiangmuu.home;

import com.example.xiangmuu.net.api.INetCallBack;

public class NewsFragmentContract {


    public interface INewsView {
        void  setRecommendList(NewsBean newsBean);
    }
    public interface INewsMode{
        <T> void getRecommendList(String tabID, INetCallBack<T> iNetCallBack);
    }
    public interface INewsPresenter{
        void getRecommend(String string);
    }
}
