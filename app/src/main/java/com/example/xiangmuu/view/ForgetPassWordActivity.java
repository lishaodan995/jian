package com.example.xiangmuu.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiangmuu.R;
import com.example.xiangmuu.base.BaseActivity;
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.contract.ForgetPWContract;
import com.example.xiangmuu.presenter.ForgetPWPresenter;


public class ForgetPassWordActivity extends BaseActivity<ForgetPWPresenter> implements ForgetPWContract.IForgetPWView {

    private String phoneNum;
    private EditText cell_phone_num;
    private EditText import_verified;
    private TextView verified_get;

    private Button next_but;
    private String verified_str;


    @Override
    protected ForgetPWPresenter initPresenter() {
        return new ForgetPWPresenter();
    }

    @Override
    public void initView() {

        Intent intent = getIntent();

        phoneNum = intent.getStringExtra("phoneNum");

        cell_phone_num = findViewById(R.id.cell_phone_num);
          import_verified = findViewById(R.id.import_verified);
          verified_get= findViewById(R.id.verified_get);
          next_but= findViewById(R.id.next_but);



        cell_phone_num.setText(phoneNum);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initLinstener() {


        next_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verified_str = import_verified.getText().toString().trim();

                if(!TextUtils.isEmpty(phoneNum) && !TextUtils.isEmpty(verified_str)){

                    mPresenter.checkSmsCode(phoneNum, verified_str,"2");

                }else{

                }
            }
        });



        verified_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getVerified(phoneNum,"2");

            }

        });

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    public void getVerified(VerfiedBean bean) {

        if(bean.getCode() ==1) Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "验证码发送失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {

        if(verfiedBean.getCode()==1){


            Intent it = new Intent(ForgetPassWordActivity.this,AffirmPassWordActivity.class);
            it.putExtra("phoneNum",phoneNum);
            it.putExtra("verified_str",verified_str);
            startActivity(it);
        }
    }
}
