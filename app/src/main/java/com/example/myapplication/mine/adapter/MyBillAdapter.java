package com.example.myapplication.mine.adapter;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;

import java.util.List;

public class MyBillAdapter extends BaseAdapter<String> {
    public MyBillAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_mybill;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String string, int position) {

    }
}
