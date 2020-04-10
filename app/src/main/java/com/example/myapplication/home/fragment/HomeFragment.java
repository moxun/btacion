package com.example.myapplication.home.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.home.adapter.HomeHeyueAdapter;
import com.example.myapplication.home.adapter.HomeModleAdapter;
import com.example.myapplication.mine.adapter.MineModleAdapter;
import com.example.myapplication.utils.VerticalSwipeRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xuezj.cardbanner.CardBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.app_back)
    ImageView appBack;
    @BindView(R.id.banner)
    CardBanner banner;
    @BindView(R.id.fenge)
    View fenge;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.queeView)
    LinearLayout queeView;
    @BindView(R.id.recy_modle)
    RecyclerView recyModle;
    @BindView(R.id.yaoqing)
    TextView yaoqing;
    @BindView(R.id.xinwen)
    TextView xinwen;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.heyue_back)
    View heyueBack;
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
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    private List<ModuleBean> moduleBeans = new ArrayList<>();
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        moduleBeans.add(new ModuleBean(getString(R.string.topup), R.drawable.icon_home_chongbi, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.tibi), R.drawable.icon_home_tibi, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.kefu), R.drawable.icon_kefu, 0));
        moduleBeans.add(new ModuleBean(getString(R.string.helpcenter), R.drawable.icon_help, 0));
        recyModle.setNestedScrollingEnabled(false);
        recyModle.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyModle.setAdapter(new HomeModleAdapter(moduleBeans, getContext()));

        recyHeyue.setNestedScrollingEnabled(false);
        recyHeyue.setLayoutManager(new LinearLayoutManager(mActivity));
        recyHeyue.setAdapter(new HomeHeyueAdapter(moduleBeans));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


}
