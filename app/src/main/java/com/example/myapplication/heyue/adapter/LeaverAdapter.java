package com.example.myapplication.heyue.adapter;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.AmountsBean;

import java.util.List;

public class LeaverAdapter extends BaseAdapter<Integer> {

    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(String s);
    }
    public LeaverAdapter(List<Integer> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_amounts;
    }

    @Override
    public void addAll(List<Integer> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, Integer integer, int position) {
        holder.setText(R.id.geshu,integer+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemListener.onClick(integer+"");
            }
        });
    }
}
