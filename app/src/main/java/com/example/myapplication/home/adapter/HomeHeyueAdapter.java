package com.example.myapplication.home.adapter;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.ModuleBean;

import java.util.List;

public class HomeHeyueAdapter extends BaseAdapter<ModuleBean> {
    public HomeHeyueAdapter(List<ModuleBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_heyue;
    }

    @Override
    public void addAll(List<ModuleBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ModuleBean string, int position) {

    }
}
