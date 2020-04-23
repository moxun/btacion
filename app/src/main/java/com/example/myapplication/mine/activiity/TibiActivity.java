package com.example.myapplication.mine.activiity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Login.ForgrtPwdActivity;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.adapter.ChooseBiTypeAdapter;
import com.example.myapplication.bean.AddressBean;
import com.example.myapplication.bean.CoinsListBean;
import com.example.myapplication.bean.RegisterBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.DialogUtil;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TibiActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.choose_bi)
    TextView chooseBi;
    @BindView(R.id.saoyisao)
    ImageView saoyisao;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.keyong)
    TextView keyong;
    @BindView(R.id.ketong_size)
    TextView ketongSize;
    @BindView(R.id.dongjie_size)
    TextView dongjieSize;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.chongbi_hint)
    TextView chongbiHint;
    @BindView(R.id.next_do)
    TextView nextDo;
    @BindView(R.id.bi_type)
    TextView biType;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    @BindView(R.id.edit_size)
    EditText editSize;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.textWithdrawFree)
    TextView textWithdrawFree;
    private Dialog dialog;
    private ArrayList<AddressBean.DataBean> strings;
    private ChooseBiTypeAdapter dilogChooseAdapter;
    private List<AddressBean.DataBean> data;
    private int biid;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.tibi));
        ChooseidType();
        getAddressList();
    }


    public void ChooseidType() {
        View dialogView = baseInflater.inflate(R.layout.dialog_photo_picture, null);


        dialog = DialogUtil.showDialogBottom(this, dialogView);

        RecyclerView recy_choose = dialogView.findViewById(R.id.recy_choose);
        recy_choose.setLayoutManager(new LinearLayoutManager(this));
        strings = new ArrayList<>();
        Log.d("-----", "ChooseidType: " + strings.size());
        dilogChooseAdapter = new ChooseBiTypeAdapter(strings);
        recy_choose.setNestedScrollingEnabled(false);
        recy_choose.setAdapter(dilogChooseAdapter);
        dilogChooseAdapter.setOnItemListener(new ChooseBiTypeAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, AddressBean.DataBean type) {
                if (data.size() == 0) {
                    return;
                }
                dilogChooseAdapter.setDefSelect(pos);
                biType.setText(type.getCoin().getCoinName());
                ketongSize.setText(data.get(pos).getBalance().getAmount() + type.getCoin().getCoinName());
                textWithdrawFree.setText(type.getCoin().getWithdrawFree());
                biid=type.getCoin().getId();
                dialog.dismiss();
            }
        });


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
                            CoinsListBean.DataBean coin = response.getData().get(0).getBalance().getCoin();;
                            biType.setText(coin.getCoinName());
                            biid = coin.getId();
                            ketongSize.setText(response.getData().get(0).getBalance().getAmount() + coin.getCoinName());
                            textWithdrawFree.setText(coin.getWithdrawFree());


                        }
                    }
                }));
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            if (getcode != null) {
                getcode.setText("重新发送");
                getcode.setClickable(true);
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            if (getcode != null) {
                getcode.setClickable(false);//防止重复点击
                getcode.setText(millisUntilFinished / 1000 + "s");
            }
        }
    }

    private void getCode() {
        String phone = SharedPreferenceUtils.getPhone();
        if (StringUtils.isEmpty(phone)) {

        }
        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/send_code")
                .addHeader("X-Requested-With", "XMLHttpReques")

                .addParams("to", phone)
                .addParams("func", "withdraw")
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {

                    private TimeCount time;

                    @Override
                    public void onError(String e) {
                        Toast.makeText(TibiActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        time = new TimeCount(60000, 1000);
                        time.start();
                        getcode.setClickable(false);
                        Toast.makeText(TibiActivity.this, R.string.fscg, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void submitBi() {
        if (StringUtils.isEmpty(editSize.getText().toString())) {
            ToastUtils.showToast(getResources().getString(R.string.input_tibi_size));
            return;
        }
        if (StringUtils.isEmpty(editAddress.getText().toString())) {
            ToastUtils.showToast(getResources().getString(R.string.input_address));
            return;
        }
        if (StringUtils.isEmpty(editCode.getText().toString())) {
            ToastUtils.showToast(getResources().getString(R.string.qsrsjyzm));
            return;
        }
        OkHttpUtils.post()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/withdraw/submit")
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addParams("amount", editSize.getText().toString())
                .addParams("coinId", ""+biid)
                .addParams("address", editAddress.getText().toString())
                .addParams("code", editCode.getText().toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RegisterBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(TibiActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RegisterBean response) throws JSONException {
                        finish();
                        ToastUtils.showToast(getString(R.string.hint_tibisu));
                    }
                }));
    }

    @OnClick({R.id.finish, R.id.choose_bi, R.id.getcode, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.choose_bi:
                dialog.show();
                break;
            case R.id.getcode:
                getCode();
                break;
            case R.id.next_do:
                submitBi();
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tibi;
    }


}
