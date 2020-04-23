package com.example.myapplication.mine.activiity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.myapplication.base.adapter.DilogChooseAdapter;
import com.example.myapplication.bean.ShimingBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.DialogUtil;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;

public class ShimingActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.re_cardtype)
    RelativeLayout reCardtype;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_cardnumber)
    EditText editCardnumber;
    @BindView(R.id.next_do)
    TextView nextDo;
    @BindView(R.id.idcard_type)
    TextView idcardType;
    private LayoutInflater baseInflater;
    private Dialog dialog;
    private String idtype = "";

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        baseInflater = LayoutInflater.from(this);
        toolTitle.setText(getString(R.string.shiming));
        dialog = ChooseCard();
    }



    protected Dialog ChooseCard() {
        View dialogView = baseInflater.inflate(R.layout.dialog_photo_picture, null);
        final Dialog dialog = DialogUtil.showDialogBottom(this, dialogView);
        RecyclerView recy_choose = dialogView.findViewById(R.id.recy_choose);
        recy_choose.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("身份证");
        strings.add("护照");
        DilogChooseAdapter dilogChooseAdapter = new DilogChooseAdapter(strings);
        recy_choose.setNestedScrollingEnabled(false);
        recy_choose.setAdapter(dilogChooseAdapter);
        dilogChooseAdapter.setOnItemListener(new DilogChooseAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, String type) {
                dilogChooseAdapter.setDefSelect(pos);
                idtype = type;
                idcardType.setText(idtype);
                dialog.dismiss();
            }
        });

        return dialog;
    }

    @OnClick({R.id.finish, R.id.re_cardtype, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.re_cardtype:
                dialog.show();
                break;
            case R.id.next_do:
                getinFo();
                break;
        }
    }

    private void getinFo() {

        if (StringUtils.isEmpty(editName.getText().toString())) {

            ToastUtils.showToast(getString(R.string.qsuzjxm));
            return;
        }
        if (StringUtils.isEmpty(editCardnumber.getText().toString())) {

            ToastUtils.showToast(getString(R.string.qsuzjh));
            return;
        }
        if(idtype.equals("")){
            ToastUtils.showToast(getString(R.string.choose_idcard));
            return;
        }
        JSONObject json = new JSONObject();

        try {
            json.put("backPhoto", "");
            json.put("idcard", editCardnumber.getText().toString());
            json.put("name", editName.getText().toString());
            json.put("positivePhoto", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("----------",json.toString());
        OkHttpUtils.postString().url(ZgwApplication.appRequestUrl + "certification/submit")


                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .mediaType(MediaType.parse("application/json"))
                .content(json.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<ShimingBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(ShimingBean response) throws JSONException {

                        ToastUtils.showToast(getString(R.string.zmcg));
                        finish();
                    }
                }));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shiming;
    }
}
