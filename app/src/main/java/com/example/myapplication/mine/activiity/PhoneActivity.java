package com.example.myapplication.mine.activiity;

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

public class PhoneActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.edit_cardnumber)
    EditText editCardnumber;
    @BindView(R.id.next_do)
    TextView nextDo;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.phonerenzheng));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone;
    }




    @OnClick({R.id.finish, R.id.getcode, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.getcode:
                break;
            case R.id.next_do:
                break;
        }
    }
}
