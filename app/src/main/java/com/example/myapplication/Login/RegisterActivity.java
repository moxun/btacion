package com.example.myapplication.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.choose_bi)
    TextView chooseBi;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.input_psw)
    EditText inputPsw;
    @BindView(R.id.input_psw_again)
    EditText inputPswAgain;
    @BindView(R.id.yaoqing_code)
    EditText yaoqingCode;
    @BindView(R.id.seek_img)
    ImageView seekImg;
    @BindView(R.id.next_do)
    TextView nextDo;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.finish, R.id.choose_bi, R.id.getcode, R.id.seek_img, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.choose_bi:
                break;
            case R.id.getcode:
                break;
            case R.id.seek_img:
                break;
            case R.id.next_do:
                break;
        }
    }
}
