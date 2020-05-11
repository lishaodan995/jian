package com.example.xiangmuu.model;

import android.util.Log;;

import com.example.xiangmuu.contract.ForgetPWContract;
import com.example.xiangmuu.net.api.INetCallBack;
import com.example.xiangmuu.net.api.NetWorkFactory;
import com.example.xiangmuu.net.api.ParamsUtils;
import com.example.xiangmuu.net.api.URLConstants;

import java.util.HashMap;

public class ForgetPWModel implements ForgetPWContract.IForgetPWMode{
    @Override
    public <T> void getVerified(String phoneNum, String type, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("type",type);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().post(URLConstants.SENDVERIFIED,commonParams,iNetCallBack);

    }

    @Override
    public <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallBack<T> iNetCallBack) {
        Log.e("TAG",phoneNum+"验证m层码值："+smsCode);



        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("type",type);
        commonParams.put("sms_code",smsCode);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().post(URLConstants.CHECKSMSCODE,commonParams,iNetCallBack);


    }
}
