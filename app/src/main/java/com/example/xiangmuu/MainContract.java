package com.example.xiangmuu;

import com.example.xiangmuu.net.api.INetCallBack;



public class MainContract {

        public interface IMainView {

        }

        public interface IMainMode{
          <T>  void getRecommendList(INetCallBack<T> netCallBack);
        }

        public interface IMainPresenter{

            void getRecommendList();
        }
}
