package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.NoticeBean;
import com.example.myapplication.bean.OrderBean;
import com.example.myapplication.mine.adapter.HistoryOrderAdapter;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.ToastUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryActivity extends BaseFragment {


    @BindView(R.id.recy_history)
    PullLoadMoreRecyclerView recyHistory;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private HistoryOrderAdapter historyOrderAdapter;
    private int page = 1;
    private ArrayList<OrderBean.DataBean> historyorders;



    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

        historyorders = new ArrayList<>();

        historyOrderAdapter = new HistoryOrderAdapter(historyorders,mActivity);
        recyHistory.setLinearLayout();
        recyHistory.setAdapter(historyOrderAdapter);
        getList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    private void getList() {
        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/point/history")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("symbolId",1+"")
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<OrderBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(OrderBean response) throws JSONException {
                       recyHistory.setPullLoadMoreCompleted();
                        if (page == 1) {
                            if (linearModle == null) {
                                return;
                            }
                            if (response.getData().size() > 0) {
                                linearModle.setVisibility(View.GONE);
                                recyHistory.setVisibility(View.VISIBLE);
                                historyorders.addAll(response.getData());
                                historyOrderAdapter.notifyDataSetChanged();
                            } else {

                                recyHistory.setVisibility(View.GONE);
                                linearModle.setVisibility(View.VISIBLE);
                            }

                        } else {
                            if (response.getData().size() > 0) {
                                historyorders.addAll(response.getData());
                                historyOrderAdapter.notifyDataSetChanged();
                            } else {
                                recyHistory.setPushRefreshEnable(false);
                            }
                        }
                    }
                }));
    }



}
