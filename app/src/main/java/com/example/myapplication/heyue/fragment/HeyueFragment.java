package com.example.myapplication.heyue.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.base.adapter.NewsListadapter;
import com.example.myapplication.bean.ChicangBean;
import com.example.myapplication.bean.MyCangweiBean;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.bean.SymbolBean;
import com.example.myapplication.mine.activiity.HistoryActivity;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.DialogUtil;
import com.example.myapplication.utils.MoneyUtils;
import com.example.myapplication.utils.NeViewpager;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeyueFragment extends BaseFragment {


    @BindView(R.id.bapzheng)
    TextView bapzheng;
    @BindView(R.id.ll_dianwei)
    LinearLayout llDianwei;
    @BindView(R.id.tab_dianwei)
    TabLayout tabDianwei;
    @BindView(R.id.viewpager_dianwei)
    NeViewpager viewpagerDianwei;
    @BindView(R.id.fukui)
    TextView fukui;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.ll_ll)
    LinearLayout llLl;
    @BindView(R.id.sckc)
    TextView sckc;
    @BindView(R.id.zy)
    EditText zy;
    @BindView(R.id.chongbi_address)
    EditText chongbiAddress;
    @BindView(R.id.copy)
    TextView copy;
    @BindView(R.id.re_zhang)
    RelativeLayout reZhang;
    @BindView(R.id.marukaiduo)
    TextView marukaiduo;
    @BindView(R.id.duo_kekai)
    TextView duoKekai;
    @BindView(R.id.maichukk)
    TextView maichukk;
    @BindView(R.id.kong_kekai)
    TextView kongKekai;
    @BindView(R.id.ll_yongxu)
    RelativeLayout llYongxu;
    @BindView(R.id.ll_dian_2)
    LinearLayout llDian2;
    @BindView(R.id.zhuanhuantype)
    ImageView zhuanhuantype;
    @BindView(R.id.symbol)
    TextView symbol;
    @BindView(R.id.zhishujia)
    TextView zhishujia;
    @BindView(R.id.fee)
    TextView fee;
    @BindView(R.id.ganggangjine)
    TextView ganggangjine;
    @BindView(R.id.shouxu)
    TextView fee1;
    @BindView(R.id.dingdanjine)
    TextView dingdanjine;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.edit_leave)
    EditText editLeave;
    @BindView(R.id.kaiduo)
    TextView kaiduo;
    @BindView(R.id.kaikong)
    TextView kaikong;

    private boolean type = true;//点位
    private ArrayList<String> strings;
    private HistoryActivity historyActivity;
    private ArrayList<Fragment> fragments;
    private NewsListadapter newsListadapter;
    private ChiCangFragment chiCangFragment;
    private int id;
    private double nowprice;
    private boolean isPrice=true;//市场价
    public HeyueFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
//        Submit();
        getSyoble();
        settingsDianwei();
        strings = new ArrayList<>();
        strings.add(getString(R.string.chicang));
        strings.add(getString(R.string.history_order));
        fragments = new ArrayList<>();

        newsListadapter = new NewsListadapter(getChildFragmentManager(), fragments, strings);
        viewpagerDianwei.setAdapter(newsListadapter);
        tabDianwei.setInlineLabel(true);


        tabDianwei.setupWithViewPager(viewpagerDianwei);

        viewpagerDianwei.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabDianwei));
        sckc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPrice){
                    zy.setText(getString(R.string.ydqzyjgxs));
                    zy.setEnabled(true);
                    zy.setText("");
                    sckc.setText(getString(R.string.xianjiakacang));
                    zy.setHint(getString(R.string.input_price));
                    isPrice=false;
                }else {
                    sckc.setText(getString(R.string.sckc));
                    zy.setText(getString(R.string.ydqzyjgxs));
                    zy.setEnabled(false);
                    isPrice=true;
                }

            }
        });

        chongbiAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals("")){
                    int i = Integer.parseInt(s.toString());
                    copy.setText("张≈"+(i*0.001)+"BTC");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void settingsDianwei() {

    }

    public void Cangwei(int id) {
        OkHttpUtils.get()
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .url(ZgwApplication.appRequestUrl + "order/position")
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
                        bapzheng.setText(response.getData().getMargin());
                    }
                }));
    }

    private void getInfo() {//获取我的仓位信息
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/info")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("symbolId", id + "")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<ChicangBean>() {


                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {
                        if (response.getData() != null) {
                            ChicangBean.DataBean data = response.getData();
                            bapzheng.setText(data.getMargin() + "");

                            dingdanjine.setText(data.getMargin() + "");
                            fee1.setText(data.getFee() + "");
                            ganggangjine.setText(data.getLeverageAmount());
                            double d = ((data.getOpenPrice() - nowprice) * Math.abs(data.getMargin()) * data.getLeverage()) - data.getFee();

                            BigDecimal bigDecimal = MoneyUtils.decimalByUp(2, new BigDecimal(d));

                            fukui.setText(bigDecimal + "");
                        }

                    }
                }));
    }

    public void Zhishijian(int id) {//获取指示价格
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/symbol/instruments")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addParams("symbolId", id + "")
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<RegisterBean>() {


                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        zhishujia.setText(response.getData());

                    }
                }));
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
                        try {
                            SharedPreferenceUtils.setnowprice(response.getMessage());
                            nowprice = Double.parseDouble(response.getData());

                            getInfo();
                        } catch (NumberFormatException ignored) {
                            ignored.printStackTrace();

                        }

                    }
                }));
    }

    private void getSyoble() {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/symbol")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<SymbolBean>() {


                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(SymbolBean response) throws JSONException {
                        if (response.getData().size() > 0) {
                            symbol.setText(response.getData().get(0).getSymbol());
                            id = response.getData().get(0).getId();
                            chiCangFragment = new ChiCangFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", id);
                            chiCangFragment.setArguments(bundle);
                            historyActivity = new HistoryActivity();
                            fragments.add(chiCangFragment);
                            fragments.add(historyActivity);
                            newsListadapter.notifyDataSetChanged();
                            Zhishijian(id);
                            getDangqianPrice(id);
                        }

                    }
                }));
    }


    private void SubMint(int amount, int lever, int price, int symbolId, int stopLoss, int stopProfit) {

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", amount);
        jsonObject.put("lever", lever);
        jsonObject.put("price", price);
        jsonObject.put("stopLoss", stopLoss);
        jsonObject.put("stopProfit", stopProfit);
        jsonObject.put("symbolId", symbolId);
        jsonObject.put("type","perpetual");

        OkHttpUtils.postString()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/submit")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<ChicangBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {
                        ToastUtils.showToast(response.getMessage());

                    }
                }));
    }

    protected Dialog dialogPrompt() {
        View dialogView = baseInflater.inflate(R.layout.diaolg_zhiyzhis, null);
        final Dialog dialog = DialogUtil.showDialogCenter(mActivity, dialogView, 300);
        ImageView zy_remove = dialogView.findViewById(R.id.zy_remove);
        ImageView zy_add = dialogView.findViewById(R.id.zy_add);
        ImageView zs_remove = dialogView.findViewById(R.id.zs_remove);
        ImageView zs_add = dialogView.findViewById(R.id.zs_add);
        TextView textView = dialogView.findViewById(R.id.zy_size);
        TextView zs_size = dialogView.findViewById(R.id.zs_size);
        TextView cancle = dialogView.findViewById(R.id.cancle);
        TextView sure = dialogView.findViewById(R.id.sure);
        dialog.setCanceledOnTouchOutside(true);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return dialog;
    }

    private void stop(String profit, String loss) {

        if (StringUtils.isEmpty(editText.getText().toString())) {
            ToastUtils.showToast(getString(R.string.input_bzj));
            return;
        }
        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/stop")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addParams("loss", loss)
                .addParams("profit", profit)


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

    @OnClick(R.id.zhuanhuantype)
    public void onViewClicked() {
        if (type) {
            strings.clear();
            fragments.clear();
            strings.add(getString(R.string.chicang));
            strings.add(getString(R.string.dqwt));
            strings.add(getString(R.string.accomplish));
            strings.add(getString(R.string.cancle));
            fragments.add(new YxListFragment());
            fragments.add(new YxListFragment());
            fragments.add(new YxListFragment());
            fragments.add(new YxListFragment());
            newsListadapter.notifyDataSetChanged();
            llYongxu.setVisibility(View.VISIBLE);
            llDianwei.setVisibility(View.GONE);
            llDian2.setVisibility(View.GONE);
            type = false;
        } else {
            strings.clear();
            fragments.clear();
            strings.add(getString(R.string.chicang));
            strings.add(getString(R.string.history_order));

            fragments.add(chiCangFragment);
            fragments.add(historyActivity);
            newsListadapter.notifyDataSetChanged();
            llYongxu.setVisibility(View.GONE);
            llDianwei.setVisibility(View.VISIBLE);
            llDian2.setVisibility(View.VISIBLE);
            type = true;
        }
    }

    public void DianweiTijiao(int amount, int lever, int price, int symbolId, int stopLoss, int stopProfit) {

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", amount);
        jsonObject.put("lever", lever);
        jsonObject.put("price", price);
        jsonObject.put("stopLoss", stopLoss);
        jsonObject.put("stopProfit", stopProfit);
        jsonObject.put("symbolId", symbolId);

        OkHttpUtils.postString()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/submit")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<ChicangBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {
                        ToastUtils.showToast(response.getMessage());
                        Zhishijian(response.getData().getSymbolId());
                        getDangqianPrice(response.getData().getSymbolId());
                    }
                }));
    }


    @OnClick({R.id.kaiduo, R.id.kaikong,R.id.marukaiduo,R.id.maichukk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kaiduo:
                if (StringUtils.isEmpty(editLeave.getText().toString())) {

                    ToastUtils.showToast(getString(R.string.input_leave));
                    return;
                }

//                Submit(1,Integer.parseInt(editLeave.getText().toString()),0,-1,-1);
                DianweiTijiao(1, Integer.parseInt(editLeave.getText().toString()), 0, id, -1, -1);
                break;
            case R.id.kaikong:
                if (StringUtils.isEmpty(editLeave.getText().toString())) {
                    ToastUtils.showToast(getString(R.string.input_leave));
                    return;
                }

                DianweiTijiao(-1, Integer.parseInt(editLeave.getText().toString()), 0, id, -1, -1);
                break;
            case R.id.marukaiduo:

                if(isPrice){
                    SubMint(1,200,0,id,0,0);
                }else {
                    if (StringUtils.isEmpty(zy.getText().toString())) {
                        ToastUtils.showToast(getString(R.string.input_price));
                        return;
                    }
                    SubMint(1,200,Integer.parseInt(zy.getText().toString()),id,0,0);
                }
                break;
            case R.id.maichukk:
                if(isPrice){
                    SubMint(-1,200,0,id,0,0);
                }else {
                    if (StringUtils.isEmpty(zy.getText().toString())) {
                        ToastUtils.showToast(getString(R.string.input_price));
                        return;
                    }
                    SubMint(-1,200,Integer.parseInt(zy.getText().toString()),id,0,0);
                }
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_heyue;
    }

    public void changView() {
        Log.d("-------", "changView: ");
        dialogPrompt().show();
    }
}
