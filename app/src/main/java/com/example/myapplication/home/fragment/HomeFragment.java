package com.example.myapplication.home.fragment;


import android.content.Context;
import android.content.Intent;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.BannerBean;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.bean.NoticeBean;
import com.example.myapplication.bean.TickerinfoBean;
import com.example.myapplication.home.activiity.YaoqingActivity;
import com.example.myapplication.home.adapter.HomeHeyueAdapter;
import com.example.myapplication.home.adapter.HomeModleAdapter;
import com.example.myapplication.mine.activiity.NoticeActivity;
import com.example.myapplication.mine.activiity.NoticeInfoActivity;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.ToastUtils;
import com.example.myapplication.utils.VerticalSwipeRefreshLayout;
import com.google.gson.Gson;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xuezj.cardbanner.CardBanner;
import com.xuezj.cardbanner.ImageData;
import com.xuezj.cardbanner.imageloader.CardImageLoader;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements  SwipeRefreshLayout.OnRefreshListener{

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
    @BindView(R.id.jiage)
    TextView jiage;
    @BindView(R.id.image_shiShi)
    ImageView imageShiShi;
    @BindView(R.id.lin_shiShi)
    RelativeLayout linShiShi;
    @BindView(R.id.image_zhangDie)
    ImageView imageZhangDie;
    @BindView(R.id.lin_zhangDie)
    RelativeLayout linZhangDie;
    @BindView(R.id.ll_re)
    LinearLayout llRe;
    @BindView(R.id.recy_heyue)
    RecyclerView recyHeyue;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    private List<String> mGongGaoList = new ArrayList<>();//公告集合

    private List<ModuleBean> moduleBeans = new ArrayList<>();
    private ArrayList<ImageData> bannerLists;
    private HomeHeyueAdapter homeHeyueAdapter;
    private List<TickerinfoBean.DataBean> lists = new ArrayList<>();
    private ArrayList<String> instrumentIdBeans;
    private int mChengJiao=1;
    private int mShiShi=1;
    private int mZhangDie=1;
    private List<TickerinfoBean.DataBean> data;

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


                        if (response.getData().getList().size() == 0) {
                            return;
                        }
                        mGongGaoList.clear();
                        for (int i = 0; i < response.getData().getList().size(); i++) {
                            mGongGaoList.add(response.getData().getList().get(i).getTitle());
                        }

                        marqueeView.startWithList(mGongGaoList);
                        marqueeView.startWithList(mGongGaoList, R.anim.anim_bottom_in, R.anim.anim_top_out);
                        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position, TextView textView) {
                                startActivity(new Intent(mActivity, NoticeInfoActivity.class).putExtra("id", response.getData().getList().get(position).getId()));
                            }
                        });
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
        bannerLists.clear();
        for (int i = 0; i < news.size(); i++) {
            ImageData imageData = new ImageData();
            imageData.setImage(ZgwApplication.urlimg + news.get(i).getImg());
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
        instrumentIdBeans = new ArrayList<>();
        refreshLayout.setOnRefreshListener(this);
        recyHeyue.setNestedScrollingEnabled(false);
        recyHeyue.setLayoutManager(new LinearLayoutManager(mActivity));
        homeHeyueAdapter = new HomeHeyueAdapter(lists, mActivity);
        recyHeyue.setAdapter(homeHeyueAdapter);
        instrumentIdBeans.add("BTC-USDT");
        instrumentIdBeans.add("ETH-USDT");
        instrumentIdBeans.add("LTC-USDT");
        instrumentIdBeans.add("EOS-USDT");
        getListinfo(instrumentIdBeans);
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

                        setList(response.getData().getList());
                    }
                }));
    }


    public void getListinfo(List<String> type) {
        lists.clear();
        MediaType mediaType = MediaType.parse("application/json");

        String s = new Gson().toJson(type);

        OkHttpUtils.postString()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/public/ticker/info")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())
                .mediaType(mediaType)
                .content(s)
                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<TickerinfoBean>() {

                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(TickerinfoBean response) throws JSONException {
                        data = response.getData();
                        lists.addAll(response.getData());
                        homeHeyueAdapter.notifyDataSetChanged();
                    }
                }));
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.lin_chengJiao, R.id.lin_shiShi, R.id.lin_zhangDie,R.id.yaoqing, R.id.xinwen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_chengJiao:
                mShiShi = 1;
                imageShiShi.setImageResource(R.drawable.image_hq_moren);
                mZhangDie = 1;
                imageZhangDie.setImageResource(R.drawable.image_hq_moren);
                if (mChengJiao == 1) {
                    mChengJiao = 2;
                    imageChengJiao.setImageResource(R.drawable.image_hq_shenxu);
                    paiXu(1, 1);
                } else if (mChengJiao == 2) {
                    mChengJiao = 3;
                    imageChengJiao.setImageResource(R.drawable.image_hq_jiangxu);
                    paiXu(1, 2);
                } else if (mChengJiao == 3) {
                    mChengJiao = 1;
                    imageChengJiao.setImageResource(R.drawable.image_hq_moren);
                    paiXu(1, 3);
                }
                break;
            case R.id.lin_shiShi:
                mChengJiao = 1;
                imageChengJiao.setImageResource(R.drawable.image_hq_moren);
                mZhangDie = 1;
                imageZhangDie.setImageResource(R.drawable.image_hq_moren);
                if (mShiShi == 1) {
                    mShiShi = 2;
                    imageShiShi.setImageResource(R.drawable.image_hq_shenxu);
                    paiXu(2, 1);
                } else if (mShiShi == 2) {
                    mShiShi = 3;
                    imageShiShi.setImageResource(R.drawable.image_hq_jiangxu);
                    paiXu(2, 2);
                } else if (mShiShi == 3) {
                    mShiShi = 1;
                    imageShiShi.setImageResource(R.drawable.image_hq_moren);
                    paiXu(2, 3);
                }
                break;
            case R.id.lin_zhangDie:
                mChengJiao = 1;
                imageChengJiao.setImageResource(R.drawable.image_hq_moren);
                mShiShi = 1;
                imageShiShi.setImageResource(R.drawable.image_hq_moren);
                if (mZhangDie == 1) {
                    mZhangDie = 2;
                    imageZhangDie.setImageResource(R.drawable.image_hq_shenxu);
                    paiXu(3, 1);
                } else if (mZhangDie == 2) {
                    mZhangDie = 3;
                    imageZhangDie.setImageResource(R.drawable.image_hq_jiangxu);
                    paiXu(3, 2);
                } else if (mZhangDie == 3) {
                    mZhangDie = 1;
                    imageZhangDie.setImageResource(R.drawable.image_hq_moren);
                    paiXu(3, 3);
                }
                break;
            case R.id.yaoqing:

                startActivity(YaoqingActivity.class);
                break;
            case R.id.xinwen:
                startActivity(NoticeActivity.class);
                break;
        }
    }
    public void paiXu(int type, int state) {
        if (lists.size() != 0) {
            lists.clear();
            lists.addAll(data);
            if (type == 1) {
                if (state == 1) {
                    Collections.sort(lists, new Comparator<TickerinfoBean.DataBean>() {
                        BigDecimal bg1, bg2;

                        @Override
                        public int compare(TickerinfoBean.DataBean o1, TickerinfoBean.DataBean o2) {
                            bg1 = new BigDecimal(o1.getBaseVolume24H());
                            bg2 = new BigDecimal(o2.getBaseVolume24H());
                            if (bg2.compareTo(bg1) == 1) {
                                return 1;
                            }
                            if (bg2.compareTo(bg1) == 0) {
                                return 0;
                            }

                            return -1;
                        }
                    });
                } else if (state == 2) {
                    Collections.sort(lists, new Comparator<TickerinfoBean.DataBean>() {
                        BigDecimal bg1, bg2;

                        @Override
                        public int compare(TickerinfoBean.DataBean o1, TickerinfoBean.DataBean o2) {
                            bg1 = new BigDecimal(o2.getBaseVolume24H());
                            bg2 = new BigDecimal(o1.getBaseVolume24H());
                            if (bg2.compareTo(bg1) == 1) {
                                return 1;
                            }
                            if (bg2.compareTo(bg1) == 0) {
                                return 0;
                            }

                            return -1;
                        }
                    });
                } else if (state == 3) {

                }
            } else if (type == 2) {
                if (state == 1) {
                    Collections.sort(lists, new Comparator<TickerinfoBean.DataBean>() {
                        BigDecimal bg1, bg2;

                        @Override
                        public int compare(TickerinfoBean.DataBean o1, TickerinfoBean.DataBean o2) {
                            bg1 = new BigDecimal(o1.getLast());
                            bg2 = new BigDecimal(o2.getLast());
                            if (bg2.compareTo(bg1) == 1) {
                                return 1;
                            }
                            if (bg2.compareTo(bg1) == 0) {
                                return 0;
                            }

                            return -1;
                        }
                    });
                } else if (state == 2) {
                    Collections.sort(lists, new Comparator<TickerinfoBean.DataBean>() {
                        BigDecimal bg1, bg2;

                        @Override
                        public int compare(TickerinfoBean.DataBean o1, TickerinfoBean.DataBean o2) {
                            bg1 = new BigDecimal(o2.getLast());
                            bg2 = new BigDecimal(o1.getLast());
                            if (bg2.compareTo(bg1) == 1) {
                                return 1;
                            }
                            if (bg2.compareTo(bg1) == 0) {
                                return 0;
                            }

                            return -1;
                        }
                    });
                } else if (state == 3) {

                }
            } else if (type == 3) {
                if (state == 1) {
                    Collections.sort(lists, new Comparator<TickerinfoBean.DataBean>() {
                        BigDecimal bg1, bg2;

                        @Override
                        public int compare(TickerinfoBean.DataBean o1, TickerinfoBean.DataBean o2) {
                            float yk=(o1.getLast()-o1.getOpen24H())/o1.getOpen24H();
                            bg1 = new BigDecimal(yk);
                            bg2 = new BigDecimal((o2.getLast()-o2.getOpen24H())/o2.getOpen24H());
                            if (bg2.compareTo(bg1) == 1) {
                                return 1;
                            }
                            if (bg2.compareTo(bg1) == 0) {
                                return 0;
                            }

                            return -1;
                        }
                    });
                } else if (state == 2) {
                    Collections.sort(lists, new Comparator<TickerinfoBean.DataBean>() {
                        BigDecimal bg1, bg2;

                        @Override
                        public int compare(TickerinfoBean.DataBean o1, TickerinfoBean.DataBean o2) {
                            bg1 = new BigDecimal((o2.getLast()-o2.getOpen24H())/o2.getOpen24H());
                            bg2 = new BigDecimal((o1.getLast()-o1.getOpen24H())/o1.getOpen24H());
                            if (bg2.compareTo(bg1) == 1) {
                                return 1;
                            }
                            if (bg2.compareTo(bg1) == 0) {
                                return 0;
                            }

                            return -1;
                        }
                    });
                } else if (state == 3) {

                }
            }
            homeHeyueAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
       mChengJiao=1;
         mShiShi=1;
         mZhangDie=1;
        imageZhangDie.setImageResource(R.drawable.image_hq_moren);

        imageChengJiao.setImageResource(R.drawable.image_hq_moren);
        imageShiShi.setImageResource(R.drawable.image_hq_moren);
        getBannerList();
        getList();
        getListinfo(instrumentIdBeans);
        refreshLayout.setRefreshing(false);
    }
}
