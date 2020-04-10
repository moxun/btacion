package com.example.myapplication.mine.activiity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.adapter.DilogChooseAdapter;
import com.example.myapplication.utils.DialogUtil;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private LayoutInflater baseInflater;
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        baseInflater=LayoutInflater.from(this);
        toolTitle.setText(getString(R.string.shiming));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shiming;
    }

    protected Dialog ChooseCard() {
        View dialogView = baseInflater.inflate(R.layout.dialog_photo_picture, null);
        final Dialog dialog = DialogUtil.showDialogBottom(this, dialogView);
        RecyclerView recy_choose =  dialogView.findViewById(R.id.recy_choose);
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
                ChooseCard().show();
                break;
            case R.id.next_do:
                break;
        }
    }
}
