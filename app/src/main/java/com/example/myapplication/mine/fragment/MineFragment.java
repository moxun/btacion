package com.example.myapplication.mine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.mine.activiity.SeetingsActivity;
import com.example.myapplication.mine.adapter.MineModleAdapter;
import com.example.myapplication.mine.adapter.ResponeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.seetings)
    ImageView seetings;
    @BindView(R.id.mine_head)
    ImageView mineHead;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_id)
    TextView myId;
    @BindView(R.id.recy_modle)
    RecyclerView recyModle;
    @BindView(R.id.recy_mine_item)
    RecyclerView recyMineItem;
    private List<ModuleBean> moduleBeans = new ArrayList<>();
    private List<ModuleBean> reponses = new ArrayList<>();

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        moduleBeans.add(new ModuleBean(getString(R.string.topup), R.drawable.icon_topup, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.tibi), R.drawable.icon_tibi, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.mymoney), R.drawable.icon_mymoney, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.address), R.drawable.icon_address, 0));
        recyModle.setNestedScrollingEnabled(false);
        recyModle.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyModle.setAdapter(new MineModleAdapter(moduleBeans, getContext()));

        reponses.add(new ModuleBean(getString(R.string.mybill), R.drawable.icon_mybill, 0));
        reponses.add(new ModuleBean(getString(R.string.recommend), R.drawable.icon_recommend, 0));
        reponses.add(new ModuleBean(getString(R.string.notice), R.drawable.icon_notice, 0));
        reponses.add(new ModuleBean(getString(R.string.yijian), R.drawable.icon_yijian, 0));
        reponses.add(new ModuleBean(getString(R.string.aboutas), R.drawable.icon_aboutas, 0));
        recyMineItem.setLayoutManager(new LinearLayoutManager(mActivity));
        recyMineItem.setNestedScrollingEnabled(false);

        recyMineItem.setAdapter(new ResponeAdapter(reponses, getContext()));
    }


    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @OnClick(R.id.seetings)
    public void onViewClicked() {
        startActivity(new Intent(mActivity, SeetingsActivity.class));
    }
}
