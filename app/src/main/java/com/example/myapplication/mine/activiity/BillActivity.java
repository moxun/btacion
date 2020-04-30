package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.HistoryzhangBean;
import com.example.myapplication.bean.OrderBean;
import com.example.myapplication.mine.adapter.MyBillAdapter;
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

public class BillActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.recy_bill)
    PullLoadMoreRecyclerView recyBill;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private int page = 1;
    private ArrayList<HistoryzhangBean.DataBean.ListBean> lits;
    private MyBillAdapter heyueAdapter;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        chenjin(R.color.mainColor);

        lits = new ArrayList<>();

        heyueAdapter = new MyBillAdapter(lits);
        recyBill.setLinearLayout();
        recyBill.setAdapter(heyueAdapter);
        recyBill.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                getList();
            }

            @Override
            public void onLoadMore() {
                page++;
                getList();
            }
        });
        getList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bill;
    }

    private void getList() {
        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/user/log?pageNum="+page+"&pageSize=20")


                .addHeader("Authorization", SharedPreferenceUtils.getToken())

                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<HistoryzhangBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(HistoryzhangBean response) throws JSONException {
                        recyBill.setPullLoadMoreCompleted();
                        if (page == 1) {
                            if (linearModle == null) {
                                return;
                            }
                            lits.clear();
                            if (response.getData().getList().size() > 0) {
                                linearModle.setVisibility(View.GONE);
                                recyBill.setVisibility(View.VISIBLE);
                                lits.addAll(response.getData().getList());
                                heyueAdapter.notifyDataSetChanged();
                            } else {

                                recyBill.setVisibility(View.GONE);
                                linearModle.setVisibility(View.VISIBLE);
                            }

                        } else {
                            if (response.getData().getList().size() > 0) {
                                lits.addAll(response.getData().getList());
                                heyueAdapter.notifyDataSetChanged();
                            } else {
                                recyBill.setPushRefreshEnable(false);
                            }
                        }
                    }
                }));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
