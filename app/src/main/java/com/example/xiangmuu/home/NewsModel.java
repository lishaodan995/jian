package com.example.xiangmuu.home;

import android.util.Log;


import com.example.xiangmuu.net.api.INetCallBack;
import com.example.xiangmuu.net.api.NetWorkFactory;
import com.example.xiangmuu.net.api.ParamsUtils;
import com.example.xiangmuu.net.api.URLConstants;

import java.util.HashMap;

public class NewsModel implements NewsFragmentContract.INewsMode {
    @Override
    public <T> void getRecommendList(String tabID, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",tabID);
        commonParams.put("start","0");
        commonParams.put("number ","0");
        commonParams.put("point_time","0");
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.RECOMMEND_LIST,commonParams,iNetCallBack);

    }
}
