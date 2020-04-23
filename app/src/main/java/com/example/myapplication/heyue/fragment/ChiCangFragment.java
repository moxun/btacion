package com.example.myapplication.heyue.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChiCangFragment extends BaseFragment {

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
    private int id;
    private double nowprice;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        id = getArguments().getInt("id");
       getDangqianPrice(id);
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

                        getInfo();


                    }
                }));
    }
    private void getInfo() {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/info")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("symbolId", id + "")
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<ChicangBean>() {


                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {
                        if(response.getData()!=null){
                            llDate.setVisibility(View.VISIBLE);
                            linearModle.setVisibility(View.GONE);
                            ChicangBean.DataBean data = response.getData();

                            leave.setText("×" + response.getData().getLeverage());
                            if(data.getMargin()>0){
                                    kaikong.setText("多");
                            }else {
                                kaikong.setText("空");
                            }
                            openprice.setText(data.getOpenPrice()+"");
                            fee.setText(data.getFee()+"");
                            zhisuxian.setText(data.getStopLoss());
                            zhiyingxian.setText(data.getStopProfit());
                            double d = ((data.getOpenPrice() - nowprice) * Math.abs(data.getMargin()) * data.getLeverage()) - data.getFee();
                            BigDecimal bigDecimal = MoneyUtils.decimalByUp(2, new BigDecimal(d));
                            ykui.setText(bigDecimal+"");
                            if(d>0)
                            {
                                ykui.setTextColor(Color.parseColor("#03AD90"));
                            }else {
                                ykui.setTextColor(Color.parseColor("#F65448"));
                            }
                            benjin.setText(data.getMargin()+"");
                        }

                    }
                }));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chicang;
    }
    public void Close(int id) {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/close")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("symbolId", id + "")
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<ChicangBean>() {

                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
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
                Close(id);
                break;
            case R.id.yksz:
                List<Fragment>list=(List<Fragment>)ChiCangFragment.this.getFragmentManager().getFragments();
                for(Fragment f:list){
                    if(f!=null&&f instanceof HeyueFragment){
                        ((HeyueFragment) f).changView();
                        break;
                    }
                }
                break;
        }
    }
}
