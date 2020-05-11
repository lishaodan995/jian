package com.example.xiangmuu.home;

import com.example.xiangmuu.net.api.INetCallBack;


public class HomeContract {

        public interface IHomeView {
            void  setBannView(String string);


            void setTabList(String string);

        }
        public interface IHomeMode{
           <T> void getHomeBannview(INetCallBack<T> iNetCallBack);
            <T> void getHomeTabList(INetCallBack<T> iNetCallBack);
        }
        public interface IHomePresenter{
            void callHomeBannview(String string);
            void getBannerView();
            void getHomeTabList();
        }
}
