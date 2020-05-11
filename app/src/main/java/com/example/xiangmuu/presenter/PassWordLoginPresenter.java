package com.example.xiangmuu.presenter;


import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.bean.AffirmRegisterBean;
import com.example.xiangmuu.contract.PassWordLoginContract;
import com.example.xiangmuu.model.PassWordLoginModel;
import com.example.xiangmuu.net.api.INetCallBack;

public class PassWordLoginPresenter extends BasePresenter<PassWordLoginContract.IPassWordLoginView> implements PassWordLoginContract.IPassWordLoginPresenter {

    private PassWordLoginContract.IPassWordLoginMode iPassWordLoginMode;

    public PassWordLoginPresenter() {
        iPassWordLoginMode = new PassWordLoginModel();
    }

    @Override
    public void passWordLogin(String username, String password) {

        iPassWordLoginMode.passWordLogin(username, password, new INetCallBack<AffirmRegisterBean>() {
            @Override
            public void onSuccess(AffirmRegisterBean affirmRegisterBean) {

                mview.getPWLoginResult(affirmRegisterBean);

            }
            @Override
            public void onError(Throwable throwable) {

//                这里去通知V层刷新失败得结果


            }
        });

    }
}
