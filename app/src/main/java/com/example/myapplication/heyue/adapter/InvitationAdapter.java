package com.example.myapplication.heyue.adapter;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;

import java.util.List;

public class InvitationAdapter extends BaseAdapter<String> {
    public InvitationAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_invitation;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String string, int position) {

    }
}
