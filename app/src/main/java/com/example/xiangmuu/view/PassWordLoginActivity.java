package com.example.xiangmuu.view;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xiangmuu.R;
import com.example.xiangmuu.base.BaseActivity;
import com.example.xiangmuu.bean.AffirmRegisterBean;
import com.example.xiangmuu.contract.PassWordLoginContract;
import com.example.xiangmuu.presenter.PassWordLoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassWordLoginActivity extends BaseActivity<PassWordLoginPresenter> implements PassWordLoginContract.IPassWordLoginView {

    private EditText cell_phone_num;
    private EditText import_pass;


    private TextView forget_pass;

    private Button pass_login;
    private TextView verification_login;

    private TextView go_register;


    @Override
    protected PassWordLoginPresenter initPresenter() {
        return new PassWordLoginPresenter();
    }

    @Override
    public void initView() {
        cell_phone_num = findViewById(R.id.cell_phone_num);
        import_pass = findViewById(R.id.import_pass);

        forget_pass = findViewById(R.id.forget_pass);

        pass_login = findViewById(R.id.pass_login);

        verification_login = findViewById(R.id.verification_login);

        go_register = findViewById(R.id.go_register);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initLinstener() {

        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone_str = cell_phone_num.getText().toString().trim();

                if(!TextUtils.isEmpty(phone_str) && isMobileNO(phone_str)){
                    Intent it = new Intent(PassWordLoginActivity.this,ForgetPassWordActivity.class);

                    it.putExtra("phoneNum",phone_str);
                    startActivity(it);
                }

            }
        });
        pass_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone_str = cell_phone_num.getText().toString().trim();
                String pass_str= import_pass.getText().toString().trim();


                if (!TextUtils.isEmpty(phone_str) && !TextUtils.isEmpty(pass_str)){
                    if(isMobileNO(phone_str)){

                        mPresenter.passWordLogin(phone_str,pass_str);


                    }
                }

            }
        });
        verification_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_pasword_login;
    }

    @Override
    public void getPWLoginResult(AffirmRegisterBean bean) {
        if(bean.getCode() ==1){
        }else Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        
    }


    public static boolean isMobileNO(String mobiles){
        boolean flag = false;
        try{
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        }catch(Exception e){
            Log.e("TAG","手机号错误"+e.getMessage());
            flag = false;
        }
        return flag;
    }

}
