package com.example.myapplication.Login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;

public class ForgrtPwdActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.choose_bi)
    TextView chooseBi;
    @BindView(R.id.re_phone)
    RelativeLayout rePhone;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.re_code)
    RelativeLayout reCode;
    @BindView(R.id.input_psw)
    EditText inputPsw;
    @BindView(R.id.input_psw_again)
    EditText inputPswAgain;
    @BindView(R.id.next_do)
    TextView nextDo;
    private TimeCount time;
    private String phone;
    private String pwd;
    private String pwdagain;
    private String code;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgrt_pwd;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            if (getcode != null) {
                getcode.setText("重新发送");
                getcode.setClickable(true);
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            if (getcode != null) {
                getcode.setClickable(false);//防止重复点击
                getcode.setText(millisUntilFinished / 1000 + "s");
            }
        }
    }
    private void getCode() {
        if(StringUtils.isEmpty(inputPhone.getText().toString())){
            ToastUtils.showToast(getResources().getString(R.string.qsrdhhm));
            return;
        }
        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/send_code")
                .addHeader("X-Requested-With", "XMLHttpReques")

                .addParams("to",inputPhone.getText().toString())
                .addParams("func","pwd")
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(ForgrtPwdActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        time = new TimeCount(60000, 1000);
                        time.start();
                        getcode.setClickable(false);
                        Toast.makeText(ForgrtPwdActivity.this, R.string.fscg, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
    @OnClick({R.id.finish, R.id.getcode, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.getcode:
                getCode();
                break;
            case R.id.next_do:
                phone = inputPhone.getText().toString();
                pwd = inputPsw.getText().toString();
                pwdagain = inputPswAgain.getText().toString();
                code = editName.getText().toString();
                boolean isEmpty = StringUtils.isEmpty(phone) && StringUtils.isEmpty(code) && StringUtils.isEmpty(pwd)&&StringUtils.isEmpty(pwdagain);
                if(isEmpty){
                    ToastUtils.showToast(getString(R.string.qjxxtxwz));
                }else {
                    updatepwd();
                }
                break;
        }
    }

    private void updatepwd() {



        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/change_password")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addParams("password",pwd)
                .addParams("phone",phone)
                .addParams("code",code)
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(ForgrtPwdActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        finish();
                        ToastUtils.showToast(getString(R.string.update_sussec));
                    }
                }));
    }
}
