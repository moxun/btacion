package com.example.myapplication.hangqing.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.bean.InstrumentIdBean;
import com.example.myapplication.bean.TickerinfoBean;
import com.example.myapplication.home.adapter.HomeHeyueAdapter;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

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
public class HangqingFragment extends BaseFragment {


    @BindView(R.id.recy_heyue)
    RecyclerView recyHeyue;
    @BindView(R.id.fenge)
    View fenge;
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
    private List<TickerinfoBean.DataBean> moduleBeans = new ArrayList<>();
    private ArrayList<String> instrumentIdBeans;
    private HomeHeyueAdapter homeHeyueAdapter;

    private int mChengJiao=1;
    private int mShiShi=1;
    private int mZhangDie=1;
    private List<TickerinfoBean.DataBean> data;

    public HangqingFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        instrumentIdBeans = new ArrayList<>();

        recyHeyue.setNestedScrollingEnabled(false);
        recyHeyue.setLayoutManager(new LinearLayoutManager(mActivity));
        homeHeyueAdapter = new HomeHeyueAdapter(moduleBeans, mActivity);
        recyHeyue.setAdapter(homeHeyueAdapter);
        instrumentIdBeans.add("BTC-USDT");
        instrumentIdBeans.add("ETH-USDT");
        instrumentIdBeans.add("LTC-USDT");
        instrumentIdBeans.add("EOS-USDT");
        getListinfo(instrumentIdBeans);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hangqing;
    }


    public void getListinfo(List<String> type) {
        MediaType mediaType = MediaType.parse("application/json");

        String s = new Gson().toJson(type);
        Log.d("--------------", s);
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
                        moduleBeans.addAll(response.getData());
                        homeHeyueAdapter.notifyDataSetChanged();
                    }
                }));
    }

    public void getList() {

        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "wallet/v1/public/ticker/getInstrumentId")
                .addHeader("locale", SharedPreferenceUtils.getYuYan())
                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())

                .build()
                .execute(new ResultModelCallback(mActivity, new ResponseCallBack<InstrumentIdBean>() {

                    @Override
                    public void onError(String e) {
                        Toast.makeText(mActivity, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(InstrumentIdBean response) throws JSONException {
                        for (int i = 0; i < response.getData().size(); i++) {

                            instrumentIdBeans.add(response.getData().get(i).getInstrumentId());
                        }
                        getListinfo(instrumentIdBeans);
                    }
                }));
    }

    @OnClick({R.id.lin_chengJiao, R.id.lin_shiShi, R.id.lin_zhangDie})
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
        }
    }

    public void paiXu(int type, int state) {
        if (moduleBeans.size() != 0) {
            moduleBeans.clear();
            moduleBeans.addAll(data);
            if (type == 1) {
                if (state == 1) {
                    Collections.sort(moduleBeans, new Comparator<TickerinfoBean.DataBean>() {
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
                    Collections.sort(moduleBeans, new Comparator<TickerinfoBean.DataBean>() {
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
                    Collections.sort(moduleBeans, new Comparator<TickerinfoBean.DataBean>() {
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
                    Collections.sort(moduleBeans, new Comparator<TickerinfoBean.DataBean>() {
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
                    Collections.sort(moduleBeans, new Comparator<TickerinfoBean.DataBean>() {
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
                    Collections.sort(moduleBeans, new Comparator<TickerinfoBean.DataBean>() {
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
}
