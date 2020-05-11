package com.example.xiangmuu.contract;

import com.example.xiangmuu.base.BaseView;
import com.example.xiangmuu.bean.AffirmRegisterBean;
import com.example.xiangmuu.net.api.INetCallBack;

public class PassWordLoginContract {

        public interface IPassWordLoginView extends BaseView {
            void  getPWLoginResult(AffirmRegisterBean string);
        }
        public interface IPassWordLoginMode{
            <T> void passWordLogin(String username, String password, INetCallBack<T> iNetCallBack);
        }
        public interface IPassWordLoginPresenter{
            void passWordLogin(String username, String password);
        }
}
