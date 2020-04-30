package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.UserinfoBean;
import com.example.myapplication.heyue.activiity.HuazhuanActivity;
import com.example.myapplication.mine.adapter.HeyueAdapter;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.MoneyUtils;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.StringUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyzichanActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.zcgz)
    TextView zcgz;
    @BindView(R.id.zc_size)
    TextView zcSize;
    @BindView(R.id.zc_type)
    TextView zcType;
    @BindView(R.id.open_img)
    ImageView openImg;
    @BindView(R.id.ll_chongbi)
    LinearLayout llChongbi;
    @BindView(R.id.ll_tibi)
    LinearLayout llTibi;
    @BindView(R.id.bb_size)
    TextView bbSize;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.seek_img)
    ImageView seekImg;
    @BindView(R.id.recy_heyue)
    RecyclerView recyHeyue;
    @BindView(R.id.yang)
    TextView yang;
    private ArrayList<UserinfoBean.DataBean.BalanceModelsBean> balancelists;
    private HeyueAdapter heyueAdapter;
    private boolean Yans=false;
    private List<UserinfoBean.DataBean.BalanceModelsBean> balanceModels;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.mymoney));
        balancelists = new ArrayList<>();

        heyueAdapter = new HeyueAdapter(balancelists);
        recyHeyue.setLayoutManager(new LinearLayoutManager(this));
        recyHeyue.setAdapter(heyueAdapter);

        getUserinfo();

        editName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(StringUtils.isEmpty(editName.getText().toString())){
                    ToastUtils.showToast(getString(R.string.input_biname));
                    return false;
                }
                balancelists.clear();
                for (int i = 0; i < balanceModels.size(); i++) {
                    if(balanceModels.get(i).getCoin().getCoinName().equalsIgnoreCase(editName.getText().toString())){
                        balancelists.add(balanceModels.get(i));
                    }
                }
                heyueAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myzichan;
    }

    private void getUserinfo() {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/info")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())


                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<UserinfoBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(MyzichanActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(UserinfoBean response) throws JSONException {
                        balanceModels = response.getData().getBalanceModels();

                        balancelists.addAll(response.getData().getBalanceModels());
                        heyueAdapter.notifyDataSetChanged();
                        double mNumber = 0;
                        double mUsdt=0;
                        List<UserinfoBean.DataBean.BalanceModelsBean> beans = response.getData().getBalanceModels();
                        for (int i = 0; i < beans.size(); i++) {
                            mUsdt=mUsdt+beans.get(i).getAmount();
                            mNumber = mNumber + (beans.get(i).getAmount() * Double.parseDouble(beans.get(i).getCoin().getCoinPrice()));
                        }
                        zcSize.setText("" + MoneyUtils.decimalByUp(2, new BigDecimal(mUsdt)));
                        bbSize.setText(MoneyUtils.decimalByUp(2, new BigDecimal(mUsdt)) + "USDT");
                        yang.setText("≈￥"+MoneyUtils.decimalByUp(2, new BigDecimal(mNumber)));
                    }
                }));
    }


    @OnClick({R.id.finish, R.id.open_img, R.id.ll_chongbi, R.id.ll_tibi, R.id.search, R.id.seek_img,R.id.ll_huazhuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.open_img:
                if (Yans) {
                    Yans = false;
                    //选择状态 显示明文--设置为可见的密码
                    zcSize.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    yang.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    openImg.setImageResource(R.drawable.icon_openeyes);
                } else {
                    Yans = true;
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    zcSize.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    yang.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    openImg.setImageResource(R.drawable.icon_closeeyes);
                }
                break;
            case R.id.ll_chongbi:
                startActivity(ChongbiActivity.class);
                break;
            case R.id.ll_tibi:
                startActivity(TibiActivity.class);
                break;
            case R.id.ll_huazhuan:
                startActivity(HuazhuanActivity.class);
                break;
            case R.id.search:
                if(StringUtils.isEmpty(editName.getText().toString())){
                    ToastUtils.showToast(getString(R.string.input_biname));
                    return;
                }
                balancelists.clear();
                for (int i = 0; i < balanceModels.size(); i++) {
                    if(balanceModels.get(i).getCoin().getCoinName().equalsIgnoreCase(editName.getText().toString())){
                        balancelists.add(balanceModels.get(i));
                    }
                }
                heyueAdapter.notifyDataSetChanged();
                break;
            case R.id.seek_img:
                break;
        }
    }


}
