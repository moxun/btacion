package com.example.myapplication.mine.activiity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Login.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;

import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.ActivityMAnger;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.safe_seetings));
        isShiming();
        if(!StringUtils.isEmpty(SharedPreferenceUtils.getPhone())){
            textPhone.setText("（已绑定）"+settingphone(SharedPreferenceUtils.getPhone()));
        }
    }
    public static String settingphone(String phone) {
        String phone_s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phone_s;
    }
    private void isShiming() {
        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "certification/state")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        textRztype.setText(getString(R.string.zwtjrz));
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        textRztype.setText(getResources().getString(R.string.yirenzheng));
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seetings;
    }




    @OnClick({R.id.finish, R.id.ll_toolbar, R.id.user_name, R.id.shi_ren, R.id.phone_ren, R.id.em_renz, R.id.psd_rz, R.id.shop_psd,R.id.login_out})
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
            case R.id.login_out:
                ActivityMAnger.getInstance().finishAllActivity();
                SharedPreferenceUtils.setToken("");
                startActivity(LoginActivity.class);
                break;
        }
    }
}
