package com.example.myapplication.heyue.activiity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.heyue.adapter.InvitationAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvitationActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.right_button)
    TextView rightButton;

    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.ll_enshu)
    LinearLayout llEnshu;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.ll_em_ph)
    LinearLayout llEmPh;
    @BindView(R.id.revt_in)
    RecyclerView revtIn;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.yqtj));
        tab.addTab(tab.newTab().setText(getString(R.string.zjyqkh)));
        tab.addTab(tab.newTab().setText(getString(R.string.ecyqkh)));


        ArrayList<String> strings = new ArrayList<>();
        strings.add("---"); strings.add("---"); strings.add("---"); strings.add("---"); strings.add("---"); strings.add("---");
        revtIn.setLayoutManager(new LinearLayoutManager(this));
        revtIn.setAdapter(new InvitationAdapter(strings));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invitation;
    }




    @OnClick({R.id.finish, R.id.tv_senior, R.id.tv_junior})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                break;
            case R.id.tv_senior:
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#000000"));
                tvJunior.setTextColor(Color.parseColor("#ff999999"));
                break;
            case R.id.tv_junior:
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#ff999999"));
                tvJunior.setTextColor(Color.parseColor("#000000"));
                break;
        }
    }
}
