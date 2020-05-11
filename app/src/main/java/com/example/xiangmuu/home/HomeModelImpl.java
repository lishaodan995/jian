package com.example.xiangmuu.home;


import com.example.xiangmuu.net.api.INetCallBack;

public class HomeModelImpl implements HomeContract.IHomeMode {

    private HomeContract.IHomePresenter iHomePresenter;

    public HomeModelImpl(HomeContract.IHomePresenter iHomePresenter) {
        this.iHomePresenter = iHomePresenter;
    }

    @Override
    public <T> void getHomeBannview(INetCallBack<T> netCallBack) {
    }

    @Override
    public <T> void getHomeTabList(INetCallBack<T> iNetCallBack) {
    }
}
