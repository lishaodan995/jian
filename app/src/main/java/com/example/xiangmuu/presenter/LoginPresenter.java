package com.example.xiangmuu.presenter;

import android.util.Log;

import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.bean.AffirmRegisterBean;
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.contract.LoginContract;
import com.example.xiangmuu.model.LoginModel;
import com.example.xiangmuu.net.api.INetCallBack;


public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {

    LoginContract.ILoginMode iLoginMode;

    public LoginPresenter() {
        iLoginMode = new LoginModel();
    }

    @Override
    public void getVerified(String phoneNum,String type) {


        iLoginMode.getVerified(phoneNum, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean s) {


                mview.getVerified(s);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    public void login(String mobile, String smsCode) {
        iLoginMode.login(mobile, smsCode, new INetCallBack<AffirmRegisterBean>() {
            @Override
            public void onSuccess(AffirmRegisterBean affirmRegisterBean) {

                    Log.e("TAG","登录成功返回值："+affirmRegisterBean.toString());
                    mview.getLoginResult(affirmRegisterBean);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }


    @Override
    public void checkSmsCode(String phoneNum, String smsCode, String type) {

        Log.e("TAG",phoneNum+"验证p层码值："+smsCode);

        iLoginMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {

                mview.checkSmsCodeResult(verfiedBean);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
