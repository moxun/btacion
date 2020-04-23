package com.example.myapplication.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.kline.KlineActivity;

import java.util.List;

public class HomeHeyueAdapter extends BaseAdapter<ModuleBean> {

    private final Context context;

    public HomeHeyueAdapter(List<ModuleBean> dataList, Context context) {
        super(dataList);
        this.context =context;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, KlineActivity.class));
            }
        });
    }
}
