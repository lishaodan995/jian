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
import com.example.xiangmuu.bean.VerfiedBean;
import com.example.xiangmuu.contract.LoginContract;
import com.example.xiangmuu.presenter.LoginPresenter;
import com.tencent.mmkv.MMKV;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {


    private EditText phone_num;
    private EditText verfied;
    private TextView send_verfied_bug;
    private Button login;

    private TextView pass_login;
    private TextView login_to_reg;


    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {
          phone_num = findViewById(R.id.cell_phone_number);
          verfied= findViewById(R.id.import_verification);
          send_verfied_bug= findViewById(R.id.verification);
          login= findViewById(R.id.verification_login);
        pass_login = findViewById(R.id.pass_login);
        login_to_reg =  findViewById(R.id.login_to_reg);

    }

    @Override
    public void initData() {

    }

  private   String edit_phone_num;
   private String edit_sms_code;

    @Override
    public void initLinstener() {

        pass_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        login_to_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });


        send_verfied_bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonenum = phone_num.getText().toString();
                if (!TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)) {
                    mPresenter.getVerified(phonenum, "4");
                } else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
            }
        });
//登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_phone_num = phone_num.getText().toString();
                edit_sms_code = verfied.getText().toString();
                if (!TextUtils.isEmpty(edit_phone_num) && isMobileNO(edit_phone_num)) {
                    if (!TextUtils.isEmpty(edit_sms_code)) {
                        Pattern pattern = Pattern.compile("\\d{6}");
                        boolean matches = pattern.matcher(edit_sms_code).matches();
                        if (matches) {
                            Log.e("TAG", edit_phone_num + "验证码值：" + edit_sms_code);

                            mPresenter.checkSmsCode(edit_phone_num, edit_sms_code, "4");
                        } else
                            Toast.makeText(LoginActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
            }
        });
    }
//风控系统
    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }
    @Override
    public void getVerified(VerfiedBean s) {
        if(s.getCode() ==1){
            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getLoginResult(AffirmRegisterBean registerBean) {



        if(registerBean.getCode()==1) {

            Toast.makeText(this, "登录成功返回数据，且code等于1", Toast.LENGTH_SHORT).show();

            if (null != registerBean.getData().getToken().getValue() && registerBean.getData().getToken().getValue() != "") {

                MMKV mmkv = MMKV.defaultMMKV();

                mmkv.encode("token", registerBean.getData().getToken().getValue());
                mmkv.encode("expire_time", registerBean.getData().getToken().getExpire_time());
                mmkv.encode("head_url", registerBean.getData().getUser_info().getHead_url());
                mmkv.encode("nickname", registerBean.getData().getUser_info().getNickname());
                mmkv.encode("mobile", registerBean.getData().getUser_info().getMobile());

                Toast.makeText(this, "登录成功，跳转HomeActivity", Toast.LENGTH_SHORT).show();
                
            }
        }

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {

        if(verfiedBean.getCode() ==1){
            mPresenter.login(edit_phone_num,edit_sms_code);

        }else Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();


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
