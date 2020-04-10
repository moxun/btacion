package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTuiGuangActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.img_sy)
    ImageView imgSy;
    @BindView(R.id.text_till)
    TextView textTill;
    @BindView(R.id.img_sy1)
    ImageView imgSy1;
    @BindView(R.id.text_till1)
    TextView textTill1;
    @BindView(R.id.img_allshouxu)
    ImageView imgAllshouxu;
    @BindView(R.id.text_allsx)
    TextView textAllsx;
    @BindView(R.id.img_fri)
    ImageView imgFri;
    @BindView(R.id.text_allsize)
    TextView textAllsize;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.my_tuiguang));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_tui_guang;
    }




    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
