package com.example.myapplication.base.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 88888 on 2019/1/18.
 */

public class NewsListadapter extends FragmentPagerAdapter {


    private final ArrayList<Fragment> mFragments;
    private final FragmentManager fm;
    private final ArrayList<String> strings;

    public NewsListadapter(FragmentManager fm, ArrayList<Fragment> fragments,ArrayList<String> strings) {
        super(fm);
        this.fm = fm;
        this.strings =strings;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }



    @Override
    public long getItemId(int position) {
        return mFragments.get(position).hashCode();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (mFragments.contains(object)) {
            // 如果当前 item 未被 remove，则返回 item 的真实 position

            return mFragments.indexOf(object);
        } else {
            // 否则返回状态值 POSITION_NONE
            return POSITION_NONE;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(strings.size()>0){
            return strings.get(position);
        }else {
            return "无";
        }

    }

}
