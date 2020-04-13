package com.example.myapplication.mine.adapter;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;

import java.util.List;

public class HistoryOrderAdapter extends BaseAdapter<String> {
    public HistoryOrderAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_histry;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String string, int position) {

    }
}
