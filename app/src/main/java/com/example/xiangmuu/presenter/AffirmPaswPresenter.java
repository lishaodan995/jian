package com.example.xiangmuu.presenter;

import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.contract.AffirmPassWordContract;
import com.example.xiangmuu.model.AffirmPaswMode;
import com.example.xiangmuu.net.api.INetCallBack;

public class AffirmPaswPresenter extends BasePresenter<AffirmPassWordContract.IAffirmPaswView> implements AffirmPassWordContract.IAffirmPaswPresenter {

    private AffirmPassWordContract.IAffirmPaswMode iAffirmPaswMode;

    public AffirmPaswPresenter() {

        iAffirmPaswMode = new AffirmPaswMode();

    }

    @Override
    public void forgetPasw(String phoneNum, String sms, String password) {

        iAffirmPaswMode.forgetPasw(phoneNum, sms, password, new INetCallBack<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean bean) {

                mview.registerResult(bean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }
}
