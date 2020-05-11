package com.example.xiangmuu.presenter;
import com.example.xiangmuu.base.BasePresenter;
import com.example.xiangmuu.bean.AffirmRegisterBean;
import com.example.xiangmuu.contract.AffirmContract;
import com.example.xiangmuu.model.AffirmRegisterModel;
import com.example.xiangmuu.net.api.INetCallBack;


public class AffirmRegisterPresenter extends BasePresenter<AffirmContract.IAffirmView> implements AffirmContract.IAffirmPresenter {

    private AffirmContract.IAffirmMode iAffirmMode;
    public AffirmRegisterPresenter() {

        iAffirmMode = new AffirmRegisterModel();

    }

    @Override
    public void register(String phoneNum, String password, String affirm_password) {
        iAffirmMode.register(phoneNum, password, affirm_password, new INetCallBack<AffirmRegisterBean>() {
            @Override
            public void onSuccess(AffirmRegisterBean registerBean) {
                mview.registerResult(registerBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
