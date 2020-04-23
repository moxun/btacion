package com.example.myapplication.Login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.choose_bi)
    TextView chooseBi;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.input_psw)
    EditText inputPsw;
    @BindView(R.id.input_psw_again)
    EditText inputPswAgain;
    @BindView(R.id.yaoqing_code)
    EditText yaoqingCode;
    @BindView(R.id.seek_img)
    ImageView seekImg;
    @BindView(R.id.next_do)
    TextView nextDo;
    private int durationTime = 60 * 1000;
    private TimeCount time;
    private String yaoqing;
    private String pwdagain;
    private String pwd;
    private String code;
    private String phone;
    private boolean ischoose=false;
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }

    private void registerUser() {
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code",code);
            jsonObject.put("invitation", yaoqing);
            jsonObject.put("password",pwd);
            jsonObject.put("phone",phone);
            jsonObject.put("username",phone);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("-----", "getCode: "+jsonObject.toString());
        OkHttpUtils.postString()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/register")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(RegisterActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {

                        startActivity(LoginActivity.class);
                    }
                }));
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
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }



    @OnClick({R.id.finish, R.id.choose_bi, R.id.getcode, R.id.seek_img, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.choose_bi:
                break;
            case R.id.getcode:
                getCode();
                break;
            case R.id.seek_img:
                if(ischoose){
                    ischoose=false;
                    seekImg.setImageResource(R.drawable.icon_seek_no);
                }else {

                    ischoose=true;
                    seekImg.setImageResource(R.drawable.icon_seek_choose);
                }
                break;
            case R.id.next_do:
                phone = inputPhone.getText().toString();
                code = editName.getText().toString();
                pwd = inputPsw.getText().toString();
                pwdagain = inputPswAgain.getText().toString();
                yaoqing = yaoqingCode.getText().toString();
                if(phone.equals("")){
                    ToastUtils.showToast(getString(R.string.qsrdhhm));
                }else if(pwd.equals("")){
                    ToastUtils.showToast(getString(R.string.qsrdlmm));
                }else if(pwdagain.equals("")){
                    ToastUtils.showToast(getString(R.string.zcsrdlmm));
                }else if(code.equals("")){
                    ToastUtils.showToast(getString(R.string.qsrsjyzm));
                }else if(!pwd.equals(pwdagain)){
                    ToastUtils.showToast(getString(R.string.pwd_not));
                }else if(!ischoose) {
                    ToastUtils.showToast(getString(R.string.agree_xieyi));
                }else {
                    registerUser();
                }
                break;
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
                .addParams("func","register")
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(RegisterActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        time = new TimeCount(60000, 1000);
                        time.start();
                        getcode.setClickable(false);
                        Toast.makeText(RegisterActivity.this, R.string.fscg, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
