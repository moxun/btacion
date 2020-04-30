package com.example.myapplication.heyue.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ChicangBean;
import com.example.myapplication.bean.MyCangweiBean;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.MoneyUtils;
import com.example.myapplication.utils.SharedPreferenceUtils;

import org.json.JSONException;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class YomgxuFragment extends BaseFragment {


    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.kaikong)
    TextView kaikong;
    @BindView(R.id.leave)
    TextView leave;
    @BindView(R.id.pingcang)
    TextView pingcang;
    @BindView(R.id.yksz)
    TextView yksz;
    @BindView(R.id.openprice)
    TextView openprice;
    @BindView(R.id.benjin)
    TextView benjin;
    @BindView(R.id.fee)
    TextView fee;
    @BindView(R.id.ykui)
    TextView ykui;
    @BindView(R.id.zhiyingxian)
    TextView zhiyingxian;
    @BindView(R.id.zhisuxian)
    TextView zhisuxian;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private double yongxumargin;
    private double nowprice;
    private int id;
    private String orderid;
    private String type;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        id = getArguments().getInt("id");
        type = getArguments().getString("type");
        getDangqianPrice(id);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yomgxu;
    }

    public void getDangqianPrice(int id) {//获取当前价格
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/symbol/instruments")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("symbolId", id + "")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<RegisterBean>() {


                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        nowprice = Double.parseDouble(response.getData());

                        Cangwei();


                    }
                }));
    }

    public void Cangwei() {
        OkHttpUtils.get()
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/position")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("symbolId", id + "")
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<MyCangweiBean>() {

                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(MyCangweiBean response) throws JSONException {

                        if(response.getData()!=null){
                            llDate.setVisibility(View.VISIBLE);
                            linearModle.setVisibility(View.GONE);
                            MyCangweiBean.DataBean data = response.getData();
                            yongxumargin = data.getMargin();

                            double d = (data.getOpenPrice() - nowprice) * Math.abs(data.getMargin()) * data.getLever();
                            orderid = response.getData().getId();

                            leave.setText("×" + response.getData().getLever());
                            if(data.getMargin()>0){
                                kaikong.setText("多");
                            }else {
                                kaikong.setText("空");
                            }
                            openprice.setText(MoneyUtils.decimalByUp(2, new BigDecimal(data.getOpenPrice()))+"");


                            fee.setText(MoneyUtils.decimalByUp(2, new BigDecimal(data.getFee()))+"");
                            ykui.setText( MoneyUtils.decimalByUp(2, new BigDecimal(d))+"");
                            if(d>0)
                            {
                                ykui.setTextColor(Color.parseColor("#03AD90"));
                            }else {
                                ykui.setTextColor(Color.parseColor("#F65448"));
                            }
                            benjin.setText(MoneyUtils.decimalByUp(2, new BigDecimal(yongxumargin))+"");
                        }

                    }
                }));
    }
    public void Close(int id,String orserid) {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/close")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("orderId", orserid + "")
                .addParams("symbolId",id+"")
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<ChicangBean>() {

                    @Override
                    public void onError(String e) {

                    }

                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {

                    }
                }));
    }
    @OnClick({R.id.pingcang, R.id.yksz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pingcang:
                Close(id,orderid);
                break;
            case R.id.yksz:
                assert YomgxuFragment.this.getParentFragment() != null;
                ((HeyueFragment) (YomgxuFragment.this.getParentFragment())).changView(orderid);
                break;
        }
    }
}
