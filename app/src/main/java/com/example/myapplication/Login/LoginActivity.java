package com.example.myapplication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.HomeActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.StringUtil;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_psw)
    EditText inputPsw;
    @BindView(R.id.next_do)
    TextView nextDo;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.forget_pwd)
    TextView forgetPwd;
    private String phone;
    private String pwd;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        if(!StringUtils.isEmpty(SharedPreferenceUtils.getToken())){
            finish();
            startActivity(HomeActivity.class);
        }
        inputPsw.setLongClickable(false);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    private void login(String pass,String phone) {
        SharedPreferenceUtils.setPhone(phone);
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", pass);
        jsonObject.put("phone", phone);
        OkHttpUtils.postString()
                .url(ZgwApplication.appRequestUrl+"wallet/v1/user/login")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(LoginActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws org.json.JSONException {
                        String data = response.getData();
                        SharedPreferenceUtils.setToken(data);



                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }
                }));
    }

    @OnClick({R.id.finish, R.id.next_do, R.id.register, R.id.forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.next_do:
                phone = inputPhone.getText().toString();
                pwd = inputPsw.getText().toString();
                if(StringUtils.isEmpty(phone)){
                    ToastUtils.showToast(getResources().getString(R.string.qsrdhhm));
                    return;
                }
                if(StringUtils.isEmpty(pwd)){
                    ToastUtils.showToast(getResources().getString(R.string.qsrdlmm));
                    return;
                }
                login(pwd,phone);
                break;
            case R.id.register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.forget_pwd:
                startActivity(ForgrtPwdActivity.class);
                break;
        }
    }
}
