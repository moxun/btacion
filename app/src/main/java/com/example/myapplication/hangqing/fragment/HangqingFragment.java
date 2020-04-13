package com.example.myapplication.hangqing.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.home.adapter.HomeHeyueAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangqingFragment extends BaseFragment {


    @BindView(R.id.image_chengJiao)
    ImageView imageChengJiao;
    @BindView(R.id.lin_chengJiao)
    LinearLayout linChengJiao;
    @BindView(R.id.image_shiShi)
    ImageView imageShiShi;
    @BindView(R.id.lin_shiShi)
    LinearLayout linShiShi;
    @BindView(R.id.image_zhangDie)
    ImageView imageZhangDie;
    @BindView(R.id.lin_zhangDie)
    LinearLayout linZhangDie;
    @BindView(R.id.ll_re)
    RelativeLayout llRe;
    @BindView(R.id.recy_heyue)
    RecyclerView recyHeyue;
    private List<ModuleBean> moduleBeans = new ArrayList<>();
    public HangqingFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

        moduleBeans.add(new ModuleBean(getString(R.string.topup), R.drawable.icon_home_chongbi, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.tibi), R.drawable.icon_home_tibi, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.kefu), R.drawable.icon_kefu, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.helpcenter), R.drawable.icon_help, 0));
        recyHeyue.setNestedScrollingEnabled(false);
        recyHeyue.setLayoutManager(new LinearLayoutManager(mActivity));
        recyHeyue.setAdapter(new HomeHeyueAdapter(moduleBeans));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hangqing;
    }


}
