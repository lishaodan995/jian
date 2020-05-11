package com.example.xiangmuu.contract;


import com.example.xiangmuu.base.BaseView;
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.net.api.INetCallBack;

/**
 * 注册获取短信验证码
 */
public class RegisterMSMContract {

        public interface IRegisterMSMView extends BaseView {
            void getVerified(VerfiedBean s);

            void  getLoginResult(String string);

            void checkSmsCodeResult(VerfiedBean verfiedBean);

        }
        public interface IRegisterMSMMode{
            <T> void getVerified(String phoneNum, String type, INetCallBack<T> iNetCallBack);
            <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallBack<T> iNetCallBack);
        }
        public interface IRegisterMSMPresenter{
            void getVerified(String string, String type);

            void checkSmsCode(String phoneNum, String smsCode, String type);
        }
}
