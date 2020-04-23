package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.NoticeBean;
import com.example.myapplication.mine.adapter.NoticeAdapter;
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

public class NoticeActivity extends BaseActivity {
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private int page = 1;
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_notic)
    PullLoadMoreRecyclerView recyNotic;
    private NoticeAdapter heyueAdapter;
    private ArrayList<NoticeBean.DataBean.ListBean> noticces;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.xinwen_notice));
        recyNotic.setLinearLayout();
        noticces = new ArrayList<>();

        heyueAdapter = new NoticeAdapter(noticces, this);
        recyNotic.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                noticces.clear();
                getList();
            }

            @Override
            public void onLoadMore() {
                page++;
                getList();
            }
        });


        recyNotic.setAdapter(heyueAdapter);
        getList();
    }

    private void getList() {
        recyNotic.setPullLoadMoreCompleted();
        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/user/notice/list?pageSize=5&pageNum=" + page)

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<NoticeBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(NoticeBean response) throws JSONException {
                        if (page == 1) {
                            if(linearModle==null){
                                return;
                            }
                            if (response.getData().getList().size() > 0) {
                                linearModle.setVisibility(View.GONE);
                                recyNotic.setVisibility(View.VISIBLE);
                                noticces.addAll(response.getData().getList());
                                heyueAdapter.notifyDataSetChanged();
                            } else {

                                recyNotic.setVisibility(View.GONE);
                                linearModle.setVisibility(View.VISIBLE);
                            }

                        } else {
                            if (response.getData().getList().size() > 0) {
                                noticces.addAll(response.getData().getList());
                                heyueAdapter.notifyDataSetChanged();
                            } else {
                                recyNotic.setPushRefreshEnable(false);
                            }
                        }
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }


}
