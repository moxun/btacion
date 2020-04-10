package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChongbiActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;

    @BindView(R.id.next_do)
    TextView nextDo;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.topup));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chongbi;
    }



    @OnClick({R.id.finish, R.id.choose_bi, R.id.copy, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.choose_bi:
                break;
            case R.id.copy:
                break;
            case R.id.next_do:
                break;
        }
    }
}
