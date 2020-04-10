package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddradressActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.re_bitype)
    RelativeLayout reBitype;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.edit_cardnumber)
    EditText editCardnumber;
    @BindView(R.id.next_do)
    TextView nextDo;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.addadress));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addradress;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.finish, R.id.re_bitype, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.re_bitype:
                break;
            case R.id.next_do:
                break;
        }
    }
}
