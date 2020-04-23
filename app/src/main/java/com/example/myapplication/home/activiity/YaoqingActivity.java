package com.example.myapplication.home.activiity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.adapter.NewsListadapter;
import com.example.myapplication.bean.UserinfoBean;
import com.example.myapplication.home.fragment.YaoqingOneFragment;
import com.example.myapplication.home.fragment.YaoqingThreeFragment;
import com.example.myapplication.home.fragment.YaoqingTwoFragment;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YaoqingActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void initView() {
        getUserinfo();

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
                        Toast.makeText(YaoqingActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(UserinfoBean response) throws JSONException {
                        UserinfoBean.DataBean.UserBean user = response.getData().getUser();
                        Bundle bundle = new Bundle();
                        bundle.putString("code",user.getUserInvitation());
                        YaoqingOneFragment yaoqingOneFragment = new YaoqingOneFragment();
                        yaoqingOneFragment.setArguments(bundle);
                        YaoqingTwoFragment yaoqingTwoFragment = new YaoqingTwoFragment();
                        yaoqingTwoFragment.setArguments(bundle);
                        YaoqingThreeFragment yaoqingThreeFragment = new YaoqingThreeFragment();
                        yaoqingThreeFragment.setArguments(bundle);
                        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
                        fragments.add(yaoqingOneFragment);
                        fragments.add(yaoqingTwoFragment);
                        fragments.add(yaoqingThreeFragment);
                        chenjin(R.color.yapq2);

                        NewsListadapter newsListadapter = new NewsListadapter(getSupportFragmentManager(), fragments,new ArrayList<>());
                        viewpager.setAdapter(newsListadapter);
                        viewpager.setCurrentItem(1);
                        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                if(position==0){
                                    chenjin(R.color.fense);
                                }else if(position==1){
                                    chenjin(R.color.yapq2);
                                }else {
                                    chenjin(R.color.yao3);
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    }
                }));
    }
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yaoqing;
    }


}
