package com.example.xiangmuu.model;

import android.util.Log;

import com.example.xiangmuu.contract.AffirmPassWordContract;
import com.example.xiangmuu.net.api.INetCallBack;
import com.example.xiangmuu.net.api.NetWorkFactory;
import com.example.xiangmuu.net.api.ParamsUtils;
import com.example.xiangmuu.net.api.URLConstants;

import java.util.HashMap;

public class AffirmPaswMode implements AffirmPassWordContract.IAffirmPaswMode {
    @Override
    public <T> void forgetPasw(String mobile, String sms_code, String password, INetCallBack<T> iNetCallBack) {


        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",mobile);
        commonParams.put("password",password);
        commonParams.put("sms_code",sms_code);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.FORGET_PW,commonParams,iNetCallBack);




    }
}
