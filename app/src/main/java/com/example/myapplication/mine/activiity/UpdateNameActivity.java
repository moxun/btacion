package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Login.ForgrtPwdActivity;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNameActivity extends BaseActivity {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.right_button)
    TextView rightButton;
    @BindView(R.id.edit_name)
    EditText editName;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        rightButton.setVisibility(View.VISIBLE);
        toolTitle.setText(getString(R.string.update_name));
        rightButton.setText(getString(R.string.do_fwanchneg));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_name;
    }


    @OnClick({R.id.finish, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.right_button:
                updateName();
                break;
        }
    }

    private void updateName() {
        if(StringUtils.isEmpty(editName.getText().toString())){
            ToastUtils.showToast(getResources().getString(R.string.qsrxdmz));
            return;
        }
        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/change_username")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("userName",editName.getText().toString())

                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(UpdateNameActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        finish();
                        Toast.makeText(UpdateNameActivity.this, getResources().getString(R.string.update_sussec), Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
