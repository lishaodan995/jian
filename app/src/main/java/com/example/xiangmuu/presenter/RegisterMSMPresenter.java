package com.example.xiangmuu.presenter;


import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.contract.RegisterMSMContract;
import com.example.xiangmuu.model.RegisterMSMModel;
import com.example.xiangmuu.net.api.INetCallBack;

public class RegisterMSMPresenter extends BasePresenter<RegisterMSMContract.IRegisterMSMView> implements RegisterMSMContract.IRegisterMSMPresenter {

    private RegisterMSMContract.IRegisterMSMMode iRegisterMSMMode;

    public RegisterMSMPresenter() {
        iRegisterMSMMode = new RegisterMSMModel();
    }

    @Override
    public void getVerified(String string, String type) {

        iRegisterMSMMode.getVerified(string, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {
                mview.getVerified(verfiedBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    public void checkSmsCode(String phoneNum, String smsCode, String type) {

        iRegisterMSMMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerfiedBean>() {
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
