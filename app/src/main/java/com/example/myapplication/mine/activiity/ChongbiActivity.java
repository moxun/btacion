package com.example.myapplication.mine.activiity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.adapter.ChooseBiTypeAdapter;
import com.example.myapplication.bean.AddressBean;
import com.example.myapplication.bean.CoinsListBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.DialogUtil;
import com.example.myapplication.utils.Qrutils;
import com.example.myapplication.utils.SharedPreferenceUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChongbiActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;

    @BindView(R.id.next_do)
    TextView nextDo;

    @BindView(R.id.bi_type)
    TextView biType;
    @BindView(R.id.choose_bi)
    TextView chooseBi;
    @BindView(R.id.fenge)
    View fenge;
    @BindView(R.id.erweima)
    ImageView erweima;
    @BindView(R.id.heyue_back)
    View heyueBack;
    @BindView(R.id.copy)
    TextView copy;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.chongbi_hint)
    TextView chongbiHint;
    @BindView(R.id.right_button)
    TextView rightButton;

    @BindView(R.id.chongbi_address)
    TextView chongbiAddress;
    private Dialog dialog;
    private List<AddressBean.DataBean> data;
    private ArrayList<AddressBean.DataBean> strings;
    private ChooseBiTypeAdapter dilogChooseAdapter;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.topup));
        ChooseidType();
        getAddressList();
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

                        if (response.getData().size() != 0) {
                            data = response.getData();

                            strings.addAll(data);

                            dilogChooseAdapter.notifyDataSetChanged();
                            CoinsListBean.DataBean coin = response.getData().get(0).getBalance().getCoin();
                            ;
                            biType.setText(coin.getCoinName());
                            chongbiAddress.setText(response.getData().get(0).getRechargeAddress().getAddress());
                            Bitmap bitmap = Qrutils.generateBitmap(response.getData().get(0).getRechargeAddress().getAddress(), 1000, 1000);
                            erweima.setImageBitmap(bitmap);

                        }
                    }
                }));
    }

    public void ChooseidType() {
        View dialogView = baseInflater.inflate(R.layout.dialog_photo_picture, null);

        dialog = DialogUtil.showDialogBottom(this, dialogView);
        RecyclerView recy_choose = dialogView.findViewById(R.id.recy_choose);
        recy_choose.setLayoutManager(new LinearLayoutManager(this));
        strings = new ArrayList<>();

        dilogChooseAdapter = new ChooseBiTypeAdapter(strings);
        recy_choose.setNestedScrollingEnabled(false);
        recy_choose.setAdapter(dilogChooseAdapter);
        dilogChooseAdapter.setOnItemListener(new ChooseBiTypeAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, AddressBean.DataBean type) {
                dilogChooseAdapter.setDefSelect(pos);
                biType.setText(type.getCoin().getCoinName());
                chongbiAddress.setText(type.getRechargeAddress().getAddress());
                Bitmap bitmap = Qrutils.generateBitmap(type.getRechargeAddress().getAddress(), 1000, 1000);
                erweima.setImageBitmap(bitmap);
                dialog.dismiss();
            }
        });


    }

    @OnClick({R.id.finish, R.id.choose_bi, R.id.copy, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.choose_bi:
                dialog.show();
                break;
            case R.id.copy:
                break;
            case R.id.next_do:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chongbi;
    }
}
