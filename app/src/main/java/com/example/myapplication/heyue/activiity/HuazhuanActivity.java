package com.example.myapplication.heyue.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuazhuanActivity extends BaseActivity {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.right_button)
    TextView rightButton;

    @BindView(R.id.ll_hua)
    LinearLayout llHua;
    @BindView(R.id.bi)
    TextView bi;
    @BindView(R.id.can_shoose)
    TextView canShoose;
    @BindView(R.id.copy)
    TextView copy;
    @BindView(R.id.next_do)
    TextView nextDo;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.huahzuan));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_huazhuan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.finish, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.next_do:
                break;
        }
    }
}
