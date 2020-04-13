package com.example.myapplication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        inputPsw.setLongClickable(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }




    @OnClick({R.id.finish, R.id.next_do, R.id.register, R.id.forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.next_do:
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
