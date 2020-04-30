package com.example.myapplication.heyue.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ChicangBean;
import com.example.myapplication.bean.OrderBean;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.heyue.adapter.Chicangadapter;
import com.example.myapplication.mine.adapter.HistoryOrderAdapter;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.MoneyUtils;
import com.example.myapplication.utils.SharedPreferenceUtils;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ChiCangFragment extends BaseFragment {



    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.recy_history)
    RecyclerView recyHistory;
    private int id;
    private double nowprice;
    private Chicangadapter historyOrderAdapter;

    private ArrayList<OrderBean.DataBean> historyorders;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        id = getArguments().getInt("id");
        getDangqianPrice(id);
        historyorders = new ArrayList<>();
        historyOrderAdapter = new Chicangadapter(historyorders,mActivity);
        recyHistory.setLayoutManager(new LinearLayoutManager(mActivity));
        recyHistory.setAdapter(historyOrderAdapter);
        historyOrderAdapter.setOnItemListener(new Chicangadapter.OnItemListener() {
            @Override
            public void onClick(OrderBean.DataBean string) {
                Close(string.getSymbolId(),string.getId());
            }

            @Override
            public void setClose(OrderBean.DataBean string) {

                assert ChiCangFragment.this.getParentFragment() != null;
                ((HeyueFragment) (ChiCangFragment.this.getParentFragment())).changView(string.getId());
            }
        });
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
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<OrderBean>() {


                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(OrderBean response) throws JSONException {

                        if (response.getData().size() > 0) {
                            linearModle.setVisibility(View.GONE);
                            recyHistory.setVisibility(View.VISIBLE);
                            historyorders.addAll(response.getData());
                            historyOrderAdapter.notifyDataSetChanged();
                        } else {

                            recyHistory.setVisibility(View.GONE);
                            linearModle.setVisibility(View.VISIBLE);
                        }

                    }
                }));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    public void Close(int id,String oreserid) {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/close")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("symbolId", id + "")
                .addParams("orderId",oreserid)
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


}
