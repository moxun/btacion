package com.example.myapplication.heyue.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.HistpryOrderBean;
import com.example.myapplication.bean.SymbolBean;
import com.example.myapplication.heyue.adapter.YxOrderAdapter;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;

public class YxListFragment extends BaseFragment {
    @BindView(R.id.recy_history)
    RecyclerView recyHistory;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private int page = 0;
    private int id;
    private String type;
    private YxOrderAdapter yxOrderAdapter;
    private ArrayList<HistpryOrderBean.DataBean.ListBean> listBeans;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        id = getArguments().getInt("id");
        type = getArguments().getString("type");
        listBeans = new ArrayList<>();
        yxOrderAdapter = new YxOrderAdapter(listBeans, mActivity);
        recyHistory.setLayoutManager(new LinearLayoutManager(mActivity));
        recyHistory.setAdapter(yxOrderAdapter);
        GetList();
    }

    private void GetList() {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/order/list")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan()).
                addParams("pageNum", page + "")
                .addParams("pageSize", "20")
                .addParams("symbolId", id + "")
                .addParams("type", type)
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<HistpryOrderBean>() {


                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(HistpryOrderBean response) throws JSONException {
                        if(linearModle==null){
                            return;
                        }
                        Log.d("---------------",response.getData().getList().size()+type);
                        if (response.getData().getList().size() > 0) {

                            recyHistory.setVisibility(View.VISIBLE);
                            linearModle.setVisibility(View.GONE);
                            listBeans.addAll(response.getData().getList());
                            yxOrderAdapter.notifyDataSetChanged();
                        }else {
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
}
