package com.example.xiangmuu.view;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiangmuu.R;
import com.example.xiangmuu.base.BaseActivity;
import com.example.xiangmuu.bean.AffirmRegisterBean;
import com.example.xiangmuu.contract.AffirmContract;
import com.example.xiangmuu.home.HomeActivity;
import com.example.xiangmuu.presenter.AffirmRegisterPresenter;
import com.tencent.mmkv.MMKV;


/**
 * 确认注册
 */
public class AffirmRegisterActivity extends BaseActivity<AffirmRegisterPresenter> implements AffirmContract.IAffirmView {

    private EditText affreg_passward;
    private EditText affreg_affirmpassword;
    private Button arrirm_regbug;
    private String phoneNum;

    @Override
    protected AffirmRegisterPresenter initPresenter() {
        return new AffirmRegisterPresenter();
    }

    @Override
    public void initView() {

          affreg_passward = findViewById(R.id.affreg_passward);
          affreg_affirmpassword = findViewById(R.id.affreg_affirmpassword);
          arrirm_regbug  = findViewById(R.id.arrirm_regbug);
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        phoneNum = intent.getStringExtra("phoneNum");


    }

    @Override
    public void initLinstener() {
        arrirm_regbug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String passw = affreg_passward.getText().toString().trim();
                    String affirmPass = affreg_affirmpassword.getText().toString().trim();
                    if(!TextUtils.isEmpty(passw) && !TextUtils.isEmpty(affirmPass)){
                        if(passw.equals(affirmPass)){
                            mPresenter.register(phoneNum,passw,affirmPass);
                        }
                    }else {

                    }





                }
        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_affirm_register;
    }

    @Override
    public void registerResult(AffirmRegisterBean registerBean) {


        Log.e("TAG","注册成功返回值="+registerBean.toString());


        if(registerBean.getCode()==1){

            Toast.makeText(this, "注册成功返回数据，且code等于1", Toast.LENGTH_SHORT).show();

            if(null !=registerBean.getData().getToken().getValue() &&registerBean.getData().getToken().getValue()!="" ){


                MMKV mmkv = MMKV.defaultMMKV();



                mmkv.encode("token",registerBean.getData().getToken().getValue());
                mmkv.encode("expire_time",registerBean.getData().getToken().getExpire_time());
                mmkv.encode("head_url",registerBean.getData().getUser_info().getHead_url());
                mmkv.encode("nickname",registerBean.getData().getUser_info().getNickname());
                mmkv.encode("mobile",registerBean.getData().getUser_info().getMobile());






                Intent it = new Intent(AffirmRegisterActivity.this, HomeActivity.class);
                startActivity(it);
            }

        }else {
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
