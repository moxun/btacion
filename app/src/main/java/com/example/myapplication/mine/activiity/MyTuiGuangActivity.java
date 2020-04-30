package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.TeamBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.MoneyUtils;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTuiGuangActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.img_sy)
    ImageView imgSy;
    @BindView(R.id.text_till)
    TextView textTill;
    @BindView(R.id.img_sy1)
    ImageView imgSy1;
    @BindView(R.id.text_till1)
    TextView textTill1;
    @BindView(R.id.img_allshouxu)
    ImageView imgAllshouxu;
    @BindView(R.id.text_allsx)
    TextView textAllsx;
    @BindView(R.id.img_fri)
    ImageView imgFri;
    @BindView(R.id.text_allsize)
    TextView textAllsize;
    @BindView(R.id.right_button)
    TextView rightButton;
    @BindView(R.id.ll_toolbar)
    RelativeLayout llToolbar;
    @BindView(R.id.yesterdayAmount)
    TextView yesterdayAmount;
    @BindView(R.id.totalAmount)
    TextView totalAmount;
    @BindView(R.id.totalFee)
    TextView totalFee;
    @BindView(R.id.childSize)
    TextView childSize;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.my_tuiguang));
        getList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_tui_guang;
    }

    private void getList() {

        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/user/teamInfo")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<TeamBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(TeamBean response) throws JSONException {

                    yesterdayAmount.setText(""+MoneyUtils.decimalByUp(2,new BigDecimal(response.getData().getYesterdayAmount())));
                        totalAmount.setText(""+MoneyUtils.decimalByUp(2,new BigDecimal(response.getData().getTotalAmount())));
                        totalFee.setText(""+MoneyUtils.decimalByUp(2,new BigDecimal(response.getData().getTotalFee())));
                        childSize.setText(response.getData().getChildSize()+"");
                    }
               }));
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
