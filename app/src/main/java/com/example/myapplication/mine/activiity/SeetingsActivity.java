package com.example.myapplication.mine.activiity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeetingsActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.ll_toolbar)
    RelativeLayout llToolbar;
    @BindView(R.id.user_name)
    RelativeLayout userName;
    @BindView(R.id.text_rztype)
    TextView textRztype;
    @BindView(R.id.shi_ren)
    RelativeLayout shiRen;
    @BindView(R.id.text_phone)
    TextView textPhone;
    @BindView(R.id.phone_update)
    TextView phoneUpdate;
    @BindView(R.id.phone_ren)
    RelativeLayout phoneRen;
    @BindView(R.id.em_renz)
    RelativeLayout emRenz;
    @BindView(R.id.psd_rz)
    RelativeLayout psdRz;
    @BindView(R.id.shop_psd)
    RelativeLayout shopPsd;
    @BindView(R.id.login_out)
    TextView loginOut;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.safe_seetings));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seetings;
    }




    @OnClick({R.id.finish, R.id.ll_toolbar, R.id.user_name, R.id.shi_ren, R.id.phone_ren, R.id.em_renz, R.id.psd_rz, R.id.shop_psd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.ll_toolbar:
                break;
            case R.id.user_name:
                startActivity(new Intent(this,UpdateNameActivity.class));
                break;
            case R.id.shi_ren:
                startActivity(new Intent(this,ShimingActivity.class));
                break;
            case R.id.phone_ren:
                startActivity(new Intent(this,PhoneActivity.class));
                break;
            case R.id.em_renz:
                startActivity(new Intent(this,EmailActivity.class));
                break;
            case R.id.psd_rz:
                startActivity(new Intent(this,UpdateloginpwdActivity.class));
                break;
            case R.id.shop_psd:
                startActivity(new Intent(this,UpdateplaypsdActivity.class));
                break;
        }
    }
}
