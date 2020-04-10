package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeInfoActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.content)
    TextView content;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.notice_info));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_info;
    }




    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
