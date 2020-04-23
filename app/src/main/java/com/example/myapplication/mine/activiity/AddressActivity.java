package com.example.myapplication.mine.activiity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.AddressBean;
import com.example.myapplication.bean.UserinfoBean;
import com.example.myapplication.mine.adapter.AddressAdapter;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_book)
    RecyclerView recyBook;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.next_do)
    TextView nextDo;
    private ArrayList<AddressBean.DataBean> rechargeAddressBeanXES;
    private AddressAdapter addressAdapter;

    @Override
    protected void initView() {


    }

    private void getAddressList() {
        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/user/recharge_address")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<AddressBean>() {
                    @Override
                    public void onError(String e) {

                    }

                    @Override
                    public void onResponse(AddressBean response) throws JSONException {
                        Log.d("----------", "onResponse: ."+response.getData().size());
                        if(linearModle==null){
                            return;
                        }
                        if(response.getData().size()>0){
                            linearModle.setVisibility(View.GONE);
                            recyBook.setVisibility(View.VISIBLE);
                            rechargeAddressBeanXES.addAll(response.getData());
                            addressAdapter.notifyDataSetChanged();
                        }else {
                            linearModle.setVisibility(View.VISIBLE);
                            recyBook.setVisibility(View.GONE);
                        }
                    }
                }));
    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.address));
        rechargeAddressBeanXES = new ArrayList<>();
        addressAdapter = new AddressAdapter(rechargeAddressBeanXES);
        recyBook.setLayoutManager(new LinearLayoutManager(this));
        recyBook.setAdapter(addressAdapter);
        getAddressList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }




    @OnClick({R.id.finish, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.next_do:
                startActivity(new Intent(this,AddradressActivity.class));
                break;
        }
    }
}
