package com.example.xiangmuu.home;

import android.util.Log;

import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.net.api.INetCallBack;

public class RecommendPresenter extends BasePresenter<RecommendContract.IRecommendView> implements RecommendContract.IRecommendPresenter {


    RecommendContract.IRecommendMode iRecommendMode;

    public RecommendPresenter() {
        iRecommendMode = new RecommendModel();
    }

    @Override
    public void getColumList() {

        iRecommendMode.getColumList(new INetCallBack<ColunmBean>() {
            @Override
            public void onSuccess(ColunmBean remBean) {

                mview.setColumList(remBean);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    public void getRecommendList(String id) {

        iRecommendMode.getRecommendList(id,new INetCallBack<NewsBean>() {
            @Override
            public void onSuccess(NewsBean remBean) {

               mview.setRecommendList(remBean);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }
}
