package com.example.xiangmuu;


import android.view.KeyEvent;
import android.widget.Toast;

import com.example.xiangmuu.ap.AppManager;
import com.example.xiangmuu.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
            Toast.makeText(this, "调用P层网络请求", Toast.LENGTH_SHORT).show();
            mPresenter.getRecommendList();
    }

    @Override
    public void initLinstener() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }


    //双击退出
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP ){
            long currentTime = System.currentTimeMillis();
            if(currentTime-fristKeyBackTime>2000){
//  不能退出
                fristKeyBackTime = currentTime;
                Toast.makeText(this, "在点击一次返回键，退出当前应用", Toast.LENGTH_SHORT).show();
            }else{
                AppManager.getInstance().appExit();
            }
        }
        get(a);
        return super.onKeyUp(keyCode, event);
    }


    private long fristKeyBackTime = 0;


    int a =100;
    public void  get(int b){
        b = 200;
    }
}
