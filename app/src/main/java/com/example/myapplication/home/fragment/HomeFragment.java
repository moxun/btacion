package com.example.myapplication.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.BannerBean;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.bean.NoticeBean;
import com.example.myapplication.home.activiity.YaoqingActivity;
import com.example.myapplication.home.adapter.HomeHeyueAdapter;
import com.example.myapplication.home.adapter.HomeModleAdapter;
import com.example.myapplication.mine.activiity.NoticeActivity;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.ToastUtils;
import com.example.myapplication.utils.VerticalSwipeRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xuezj.cardbanner.CardBanner;
import com.xuezj.cardbanner.ImageData;
import com.xuezj.cardbanner.imageloader.CardImageLoader;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private List<String> mGongGaoList=new ArrayList<>();//公告集合
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
    private ArrayList<ImageData> bannerLists;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
    private void getList() {

        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/user/notice/list?pageSize=10&pageNum=1")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<NoticeBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(NoticeBean response) throws JSONException {

                        for (int i = 0; i < response.getData().getList().size(); i++) {
                            mGongGaoList.add(response.getData().getList().get(i).getTitle());
                        }

                        marqueeView.startWithList(mGongGaoList);
                        marqueeView.startWithList(mGongGaoList, R.anim.anim_bottom_in, R.anim.anim_top_out);
                    }

                }));
    }
    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }
    //填充list数据
    private void setList(final List<BannerBean.DataBean.ListBean> news) {

        bannerLists = new ArrayList<>();
        for (int i = 0; i < news.size(); i++) {
            ImageData imageData = new ImageData();
            imageData.setImage(news.get(i).getImg());
            bannerLists.add(imageData);
        }

        banner.setDatas(bannerLists).setCardImageLoader(new CardImageLoader() {
            @Override
            public void load(Context context, ImageView imageView, Object path) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                String imagePath;
                try {
                    imagePath = (String) path;
                    imagePath = imagePath.trim();
                    Glide.with(mActivity).load(imagePath).into(imageView);
                } catch (Exception e) {
                    Glide.with(mActivity).load(path).into(imageView);
                }

            }
        }).setPlay(true).setDelayTime(4500).start();

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
        recyHeyue.setAdapter(new HomeHeyueAdapter(moduleBeans,mActivity));
        getBannerList();
        getList();
    }

    private void getBannerList() {
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/user/banner/list")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())


                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<BannerBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BannerBean response) throws JSONException {

//                        setList(response.getData().getList());
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @OnClick({R.id.yaoqing, R.id.xinwen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yaoqing:

                startActivity(YaoqingActivity.class);
                break;
            case R.id.xinwen:
                startActivity(NoticeActivity.class);
                break;
        }
    }
}
