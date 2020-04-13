package com.example.myapplication.heyue.activiity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SySuccessActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        chenjin(R.color.mainColor);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sy_success;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
