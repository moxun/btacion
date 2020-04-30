package com.example.myapplication.heyue.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.base.adapter.NewsListadapter;
import com.example.myapplication.bean.AmountsBean;
import com.example.myapplication.bean.ChicangBean;
import com.example.myapplication.bean.MyCangweiBean;
import com.example.myapplication.bean.OrderBean;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.bean.SymbolBean;
import com.example.myapplication.bean.UserinfoBean;
import com.example.myapplication.heyue.adapter.AmountsAdapter;
import com.example.myapplication.heyue.adapter.LeaverAdapter;
import com.example.myapplication.heyue.adapter.SymoblAdapter;
import com.example.myapplication.mine.activiity.HistoryActivity;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.DialogUtil;
import com.example.myapplication.utils.MoneyUtils;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    ViewPager viewpagerDianwei;
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
    TextView editText;
    @BindView(R.id.edit_leave)
    TextView editLeave;
    @BindView(R.id.kaiduo)
    TextView kaiduo;
    @BindView(R.id.kaikong)
    TextView kaikong;
    @BindView(R.id.rela_baoZheng)
    RelativeLayout relaBaoZheng;
    @BindView(R.id.choose_leaver)
    RelativeLayout chooseLeaver;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.xiala)
    ImageView xiala;
    @BindView(R.id.dongjiebzo)
    TextView dongjiebzo;
    @BindView(R.id.ll_bao)
    LinearLayout llBao;

    private boolean type = true;//点位
    private ArrayList<String> strings;
    private HistoryActivity historyActivity;
    private ArrayList<Fragment> fragments;
    private NewsListadapter newsListadapter;
    private ChiCangFragment chiCangFragment;
    private int id;
    private double nowprice;
    private boolean isPrice = true;//市场价
    private double yongxumargin = 0;
    private BigDecimal dainweiyingkui;
    private double dianweimargin = 0;
    private BigDecimal yongxuying;
    private String duoshao = "==";
    private String kekai;
    private YomgxuFragment yomgxuFragment;
    private int zs;
    private int zy1;
    private PopupWindow popBz;
    private ArrayList<AmountsBean.DataBean> amounts;
    private AmountsAdapter amountsAdapter;
    private PopupWindow popleaver;
    private ArrayList leavers;
    private LeaverAdapter leaverAdapter;
    private RecyclerView mReList;
    private PopupWindow mPop;
    private ArrayList<SymbolBean.DataBean> synolds;
    private SymoblAdapter mAdapter;
    private boolean isla=false;
    private double dwdj;
    private String orderid1;
    private Dialog dialog;

    public HeyueFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }


    private void popBaoZheng() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.pop_amounts, null);
        RecyclerView recy_amounts = inflate.findViewById(R.id.recy_amounts);
        popBz = new PopupWindow(inflate, ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recy_amounts.setLayoutManager(linearLayoutManager);
        amounts = new ArrayList<>();
        amountsAdapter = new AmountsAdapter(amounts);
        recy_amounts.setAdapter(amountsAdapter);
        amountsAdapter.setOnItemListener(new AmountsAdapter.OnItemListener() {
            @Override
            public void onClick(AmountsBean.DataBean dataBean, String s) {
                popBz.dismiss();
                editText.setText(s);
                leavers.clear();
                leavers.addAll(dataBean.getLever());
                leaverAdapter.notifyDataSetChanged();
            }
        });

    }

    private void popBaoLeaver() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.pop_amounts, null);
        RecyclerView recLeaver = inflate.findViewById(R.id.recy_amounts);
        popleaver = new PopupWindow(inflate, ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recLeaver.setLayoutManager(linearLayoutManager);
        leavers = new ArrayList<Integer>();
        leaverAdapter = new LeaverAdapter(leavers);
        recLeaver.setAdapter(leaverAdapter);
        leaverAdapter.setOnItemListener(new LeaverAdapter.OnItemListener() {
            @Override
            public void onClick(String s) {
                popleaver.dismiss();
                editLeave.setText(s);
            }
        });

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
                        if (response.getData() == null) {
                            return;
                        }
                        MyCangweiBean.DataBean data = response.getData();
                        yongxumargin = data.getMargin();

                        double d = (data.getOpenPrice() - nowprice) * Math.abs(data.getMargin()) * data.getLever();
                        yongxuying = MoneyUtils.decimalByUp(2, new BigDecimal(d));

                        String replace = kekai.replace(duoshao, data.getNumber() + "");
                        kekai = replace;
                        duoKekai.setText(kekai);
                        kongKekai.setText(kekai);
                        duoshao = data.getNumber() + "";

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
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<OrderBean>() {


                    @Override
                    public void onError(String e) {
                        Log.d("-----------5", "1");
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(OrderBean response) throws JSONException {
                        if (response.getData().size() > 0) {
                            OrderBean.DataBean data = response.getData().get(response.getData().size() - 1);
                            dwdj = response.getData().get(0).getMargin();
                            dongjiebzo.setText(MoneyUtils.decimalByUp(2, new BigDecimal(dwdj)) + "");
                            dingdanjine.setText(data.getMargin() + "");
                            fee1.setText(data.getFee() + "");
                            ganggangjine.setText(data.getLeverageAmount() + "");
                            double d = ((data.getOpenPrice() - nowprice) * Math.abs(data.getMargin()) * data.getLeverage()) - data.getFee();

                            dainweiyingkui = MoneyUtils.decimalByUp(2, new BigDecimal(d));

                            fukui.setText(dainweiyingkui + "");

                            double baozheng = 0;
                            for (int i = 0; i < response.getData().size(); i++) {
                                baozheng = baozheng + response.getData().get(i).getMargin();
                            }
                            dianweimargin = baozheng;
                            bapzheng.setText(MoneyUtils.decimalByUp(2, new BigDecimal(dianweimargin)) + "");
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
                        zhishujia.setText(MoneyUtils.decimalByUp(2, new BigDecimal(response.getData())) + "");

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
                            SharedPreferenceUtils.setnowprice(response.getData());
                            nowprice = Double.parseDouble(response.getData());

                            getInfo();//获取点位仓位
                            Cangwei();//获取永续仓位
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
                        Log.d("-----------1", "1");
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(SymbolBean response) throws JSONException {
                        if (response.getData().size() > 0) {
                            synolds.addAll(response.getData());
                            mAdapter.notifyDataSetChanged();
                            symbol.setText(response.getData().get(0).getSymbol());
                            id = response.getData().get(0).getId();
                            chiCangFragment = new ChiCangFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", id);
                            chiCangFragment.setArguments(bundle);
                            historyActivity = new HistoryActivity();
                            historyActivity.setArguments(bundle);
                            fragments.add(chiCangFragment);
                            fragments.add(historyActivity);
                            newsListadapter.notifyDataSetChanged();
                            Zhishijian(id);//获取指示价格
                            getDangqianPrice(id);//获取当前成交价
                            getAmounts();//获取保证金列表
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
        jsonObject.put("type", "perpetual");

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
                        Log.d("-----------6", "1");
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {
                        ToastUtils.showToast(response.getMessage());
                        getDangqianPrice(response.getData().getSymbolId());
                        QiehuanHeyue();
                    }
                }));
    }

    private void getUserinfo() {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/info")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())

                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<UserinfoBean>() {
                    @Override
                    public void onError(String e) {
                        Log.d("-----------7", "1");
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(UserinfoBean response) throws JSONException {

                        double mUsdt = 0;
                        List<UserinfoBean.DataBean.BalanceModelsBean> beans = response.getData().getBalanceModels();
                        for (int i = 0; i < beans.size(); i++) {
                            mUsdt = mUsdt + beans.get(i).getAmount();

                        }
                        balance.setText(mUsdt + "");
                    }
                }));
    }

    protected Dialog dialogPrompt() {
        View dialogView = baseInflater.inflate(R.layout.diaolg_zhiyzhis, null);
        dialog = DialogUtil.showDialogCenter(mActivity, dialogView, 300);
        ImageView zy_remove = dialogView.findViewById(R.id.zy_remove);
        ImageView zy_add = dialogView.findViewById(R.id.zy_add);
        ImageView zs_remove = dialogView.findViewById(R.id.zs_remove);
        ImageView zs_add = dialogView.findViewById(R.id.zs_add);
        EditText textView = dialogView.findViewById(R.id.zy_size);
        EditText zs_size = dialogView.findViewById(R.id.zs_size);
        ;
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    zy1 = Integer.parseInt(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        zs_size.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    zs = Integer.parseInt(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        zy1 = Integer.parseInt(textView.getText().toString());
        zs = Integer.parseInt(zs_size.getText().toString());
        zs_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zs != 0) {

                    zs--;
                    zs_size.setText(zs + "");
                }
            }
        });
        zy_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zy1 != 0) {
                    zy1--;
                    textView.setText(zy1 + "");
                }
            }
        });
        zy_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zy1++;
                textView.setText(zy1 + "");
            }
        });
        zs_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zs++;

                zs_size.setText(zs + "");
            }
        });
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
                double profit = dianweimargin * (zy1 / 100);
                double loss = dianweimargin * (zs / 100);
                stop(profit + "", loss + "");
            }
        });
        return dialog;
    }

    private void stop(String profit, String loss) {


        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/stop")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addParams("loss", loss)
                .addParams("profit", profit)
                .addParams("orderId",orderid1)

                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<ChicangBean>() {


                    @Override
                    public void onError(String e) {
                        Log.d("-----------8", "1");
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {
                        dialog.dismiss();
                        ToastUtils.showToast(getString(R.string.see));
                    }
                }));
    }

    @OnClick(R.id.zhuanhuantype)
    public void onViewClicked() {
        if (type) {
            title.setText(getString(R.string.hy));
            QiehuanHeyue();
            llYongxu.setVisibility(View.VISIBLE);
            llDianwei.setVisibility(View.GONE);
            llDian2.setVisibility(View.GONE);
            bapzheng.setText(yongxumargin + "");
            if (yongxuying != null) {
                fukui.setText(yongxuying + "");
            } else {
                fukui.setText("0.0");
            }

            dongjiebzo.setText(MoneyUtils.decimalByUp(2, new BigDecimal(yongxumargin)) + "");
            type = false;
        } else {
            title.setText(getString(R.string.dw));
            QiehuanDian();
            llYongxu.setVisibility(View.GONE);
            llDianwei.setVisibility(View.VISIBLE);
            llDian2.setVisibility(View.VISIBLE);
            if (dainweiyingkui != null) {
                fukui.setText(dainweiyingkui + "");
            } else {
                fukui.setText("0.0");
            }
            bapzheng.setText(MoneyUtils.decimalByUp(2, new BigDecimal(dianweimargin)) + "");
            dongjiebzo.setText(MoneyUtils.decimalByUp(2, new BigDecimal(dwdj)) + "");
            type = true;
        }
    }

    private void QiehuanHeyue() {
        strings.clear();
        fragments.clear();
        strings.add(getString(R.string.chicang));
        strings.add(getString(R.string.dqwt));
        strings.add(getString(R.string.accomplish));
        strings.add(getString(R.string.cancle));
        yomgxuFragment = new YomgxuFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        yomgxuFragment.setArguments(bundle);
        fragments.add(yomgxuFragment);
        for (int i = 0; i < 3; i++) {
            YxListFragment yxListFragment = new YxListFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("id", id);

            if(i==0){
                bundle1.putString("type","submitted");
            }else if(i==1){
                bundle1.putString("type","filled");
            }else if(i==2){
                bundle1.putString("type","cancel");
            }
            yxListFragment.setArguments(bundle1);
            fragments.add(yxListFragment);
        }
        newsListadapter.notifyDataSetChanged();
    }

    public void DianweiTijiao(int amount, int lever, int price, int symbolId, int stopLoss, int stopProfit, String maing) {

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", maing);
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
                        Log.d("-----------9", "1");
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ChicangBean response) throws JSONException {
                        ToastUtils.showToast(response.getMessage());
                        Zhishijian(response.getData().getSymbolId());
                        getDangqianPrice(response.getData().getSymbolId());
                        QiehuanDian();
                    }
                }));
    }


    @OnClick({R.id.kaiduo, R.id.kaikong, R.id.marukaiduo, R.id.maichukk, R.id.edit_text, R.id.choose_leaver, R.id.ll_sym})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kaiduo:
                if (StringUtils.isEmpty(editLeave.getText().toString())) {

                    ToastUtils.showToast(getString(R.string.input_leave));
                    return;
                }
                if (StringUtils.isEmpty(editText.getText().toString())) {
                    ToastUtils.showToast(getString(R.string.input_bzj));
                    return;
                }
//                Submit(1,Integer.parseInt(editLeave.getText().toString()),0,-1,-1);
                DianweiTijiao(1, Integer.parseInt(editLeave.getText().toString()), 0, id, -1, -1, editText.getText().toString());
                break;
            case R.id.kaikong:
                if (StringUtils.isEmpty(editLeave.getText().toString())) {
                    ToastUtils.showToast(getString(R.string.input_leave));
                    return;
                }
                if (StringUtils.isEmpty(editText.getText().toString())) {
                    ToastUtils.showToast(getString(R.string.input_bzj));
                    return;
                }
                DianweiTijiao(-1, Integer.parseInt(editLeave.getText().toString()), 0, id, -1, -1, editText.getText().toString());
                break;
            case R.id.marukaiduo:

                if (isPrice) {
                    SubMint(1, 200, 0, id, 0, 0);
                } else {
                    if (StringUtils.isEmpty(zy.getText().toString())) {
                        ToastUtils.showToast(getString(R.string.input_price));
                        return;
                    }
                    if (StringUtils.isEmpty(editText.getText().toString())) {
                        ToastUtils.showToast(getString(R.string.input_bzj));
                        return;
                    }
                    SubMint(1, 200, Integer.parseInt(zy.getText().toString()), id, 0, 0);
                }
                break;
            case R.id.maichukk:
                if (isPrice) {
                    SubMint(-1, 200, 0, id, 0, 0);
                } else {
                    if (StringUtils.isEmpty(zy.getText().toString())) {
                        ToastUtils.showToast(getString(R.string.input_price));
                        return;
                    }
                    SubMint(-1, 200, Integer.parseInt(zy.getText().toString()), id, 0, 0);
                }
                break;
            case R.id.edit_text:
                popBz.showAsDropDown(relaBaoZheng);
                break;
            case R.id.choose_leaver:
                popleaver.showAsDropDown(chooseLeaver);
                break;
            case R.id.ll_sym:
                mPop.setAnimationStyle(R.style.PopAnimation);
                mPop.showAsDropDown(mReList, 0, 0);
                break;
        }
    }


    public void getAmounts() {


        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/amounts")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addParams("symbolId", id + "")

                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<AmountsBean>() {
                    @Override
                    public void onError(String e) {

                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(AmountsBean response) throws JSONException {
                        if (response.getData().size() > 0) {

                            editText.setText(MoneyUtils.decimalByUp(0, new BigDecimal(response.getData().get(0).getAmount())).toString());
                            amounts.addAll(response.getData());
                            amountsAdapter.notifyDataSetChanged();
                            leavers.addAll(response.getData().get(0).getLever());
                            editLeave.setText(response.getData().get(0).getLever().get(0) + "");
                            leaverAdapter.notifyDataSetChanged();
                        }
                    }
                }));
    }


    public void changView(String orderid) {
        orderid1 = orderid;
        dialogPrompt().show();
    }

    private void popBiZhong() {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.pop_bizhong, null);
        mReList = inflate.findViewById(R.id.reList);
        mPop = new PopupWindow(inflate, ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        mReList.setLayoutManager(manager);
        synolds = new ArrayList<>();
        mAdapter = new SymoblAdapter(synolds);
        mReList.setAdapter(mAdapter);
        mAdapter.setOnItemListener(new SymoblAdapter.OnItemListener() {
            @Override
            public void onClick(SymbolBean.DataBean s) {
                mPop.dismiss();
                symbol.setText(s.getSymbol());
                id = s.getId();
                if (type) {
                    QiehuanDian();
                    llYongxu.setVisibility(View.GONE);
                    llDianwei.setVisibility(View.VISIBLE);
                    llDian2.setVisibility(View.VISIBLE);
                    bapzheng.setText(dianweimargin + "");
                    fukui.setText(dainweiyingkui + "");
                } else {
                    QiehuanHeyue();
                    llYongxu.setVisibility(View.VISIBLE);
                    llDianwei.setVisibility(View.GONE);
                    llDian2.setVisibility(View.GONE);
                    bapzheng.setText(yongxumargin + "");
                    fukui.setText(yongxuying + "");
                }

                Zhishijian(id);
                getDangqianPrice(id);
                getAmounts();
            }
        });
    }

    private void QiehuanDian() {
        strings.clear();
        fragments.clear();
        strings.add(getString(R.string.chicang));
        strings.add(getString(R.string.history_order));

        fragments.add(chiCangFragment);
        fragments.add(historyActivity);
        newsListadapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
//        Submit();
        kekai = getString(R.string.kekai);
        getSyoble();//获取交易对列表
        title.setText(getString(R.string.dw));
        getUserinfo();//获取个人信息，这里要获取余额
        popBiZhong();
        strings = new ArrayList<>();
        strings.add(getString(R.string.chicang));
        strings.add(getString(R.string.history_order));
        fragments = new ArrayList<>();
        popBaoZheng();//选择保证金
        popBaoLeaver();//选择杠杆
        viewpagerDianwei.setOffscreenPageLimit(4);
        newsListadapter = new NewsListadapter(getChildFragmentManager(), fragments, strings);
        viewpagerDianwei.setAdapter(newsListadapter);
        tabDianwei.setInlineLabel(true);


        tabDianwei.setupWithViewPager(viewpagerDianwei);

        viewpagerDianwei.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabDianwei));
        sckc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPrice) {
                    zy.setText(getString(R.string.ydqzyjgxs));
                    zy.setEnabled(true);
                    zy.setText("");
                    sckc.setText(getString(R.string.xianjiakacang));
                    zy.setHint(getString(R.string.input_price));
                    isPrice = false;
                } else {
                    sckc.setText(getString(R.string.sckc));
                    zy.setText(getString(R.string.ydqzyjgxs));
                    zy.setEnabled(false);
                    isPrice = true;
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
                if (!s.toString().equals("")) {
                    int i = Integer.parseInt(s.toString());
                    copy.setText("张≈" + (i * 0.001) + "BTC");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        xiala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isla){
                    xiala.setImageResource(R.drawable.hy_back);
                    llBao.setVisibility(View.GONE);
                    isla=false;
                }else {
                    xiala.setImageResource(R.drawable.icon_xiangshang);
                    llBao.setVisibility(View.VISIBLE);
                    isla=true;
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_heyue;
    }


}
