package com.example.myapplication.mine.activiity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.adapter.ChooseBiTypeAdapter;
import com.example.myapplication.bean.CoinsListBean;
import com.example.myapplication.mine.adapter.AddAdressDialogAda;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.DialogUtil;
import com.example.myapplication.utils.SharedPreferenceUtils;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddradressActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.re_bitype)
    RelativeLayout reBitype;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.edit_cardnumber)
    EditText editCardnumber;
    @BindView(R.id.next_do)
    TextView nextDo;
    @BindView(R.id.bi_type)
    TextView biType;
    private Dialog dialog;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.addadress));
        dialog = ChooseidType();
    }
    public ArrayList<CoinsListBean.DataBean> getCoinsList() {
        ArrayList<CoinsListBean.DataBean> dataBeans = new ArrayList<>();
        OkHttpUtils.get().url(ZgwApplication.appRequestUrl+"wallet/v1/user/coins").addHeader("X-Requested-With", "XMLHttpReques")

                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .build().execute(new ResultModelCallback(this, new ResponseCallBack<CoinsListBean>() {
            @Override
            public void onError(String e) {

            }

            @Override
            public void onResponse(CoinsListBean response) throws JSONException {
                dataBeans.addAll(response.getData());
            }
        }));
        return dataBeans;
    }
    public Dialog ChooseidType() {
        View dialogView = baseInflater.inflate(R.layout.dialog_photo_picture, null);
        dialog = DialogUtil.showDialogBottom(this, dialogView);
        RecyclerView recy_choose = dialogView.findViewById(R.id.recy_choose);
        recy_choose.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<CoinsListBean.DataBean> strings = getCoinsList();
        Log.d("-----", "ChooseidType: " + strings.size());
        AddAdressDialogAda dilogChooseAdapter = new AddAdressDialogAda(strings);
        recy_choose.setNestedScrollingEnabled(false);
        recy_choose.setAdapter(dilogChooseAdapter);
        dilogChooseAdapter.setOnItemListener(new AddAdressDialogAda.OnItemListener() {
            @Override
            public void onClick(View v, int pos, CoinsListBean.DataBean type) {
                dilogChooseAdapter.setDefSelect(pos);
                biType.setText(type.getCoinName());
                dialog.dismiss();
            }
        });

        return dialog;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addradress;
    }


    @OnClick({R.id.finish, R.id.re_bitype, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.re_bitype:
                dialog.show();
                break;
            case R.id.next_do:
                break;
        }
    }
}
