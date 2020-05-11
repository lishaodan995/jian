package com.example.xiangmuu.home;


import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.net.api.INetCallBack;

public class HomePresenterImpl extends BasePresenter<HomeActivity> implements HomeContract.IHomePresenter {

    private HomeContract.IHomeMode  iHomeMode;

    public HomePresenterImpl() {
        iHomeMode = new HomeModelImpl(this);
    }
    @Override
    public void callHomeBannview(String string) {
        mview.setBannView(string);
    }
    @Override
    public void getBannerView() {
        iHomeMode.getHomeBannview(new INetCallBack<User>() {
            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    @Override
    public void getHomeTabList() {

    }
}
