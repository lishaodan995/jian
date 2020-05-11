package com.example.xiangmuu.presenter;


import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.contract.ForgetPWContract;
import com.example.xiangmuu.model.ForgetPWModel;
import com.example.xiangmuu.net.api.INetCallBack;

public class ForgetPWPresenter extends BasePresenter<ForgetPWContract.IForgetPWView> implements ForgetPWContract.IForgetPWPresenter {

    private ForgetPWContract.IForgetPWMode iForgetPWMode;

    public ForgetPWPresenter() {
        iForgetPWMode = new ForgetPWModel();
    }

    @Override
    public void getVerified(String string, String type) {

        iForgetPWMode.getVerified(string, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {
                mview.getVerified(verfiedBean);
            }
            @Override
            public void onError(Throwable throwable) {
//忽略这个方法，V层中没有对应得这个方法，，失败得刷新页面得方法
            }
        });

    }

    @Override
    public void checkSmsCode(String phoneNum, String smsCode, String type) {

        iForgetPWMode.checkSmsCode(phoneNum, smsCode, type, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {

                mview.checkSmsCodeResult(verfiedBean);
            }

            @Override
            public void onError(Throwable throwable) {
//当前方法没有使用，需要刷新V层
            }
        });

    }
}
