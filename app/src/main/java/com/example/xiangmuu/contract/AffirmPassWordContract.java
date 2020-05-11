package com.example.xiangmuu.contract;

import com.example.xiangmuu.base.BaseView;
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.net.api.INetCallBack;

public class AffirmPassWordContract {


    public interface IAffirmPaswView extends BaseView {
        void registerResult(VerfiedBean bean);
    }
    public interface IAffirmPaswMode{
        <T> void forgetPasw(String mobile, String sms_code, String password, INetCallBack<T> iNetCallBack);
    }
    public interface IAffirmPaswPresenter{
        void forgetPasw(String mobile, String sms_code, String password);

    }
}
