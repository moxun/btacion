package com.example.myapplication.heyue.adapter;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.SymbolBean;

import java.util.List;

public class SymoblAdapter extends BaseAdapter<SymbolBean.DataBean> {

    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(SymbolBean.DataBean s);
    }
    public SymoblAdapter(List<SymbolBean.DataBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_tibi;
    }

    @Override
    public void addAll(List<SymbolBean.DataBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, SymbolBean.DataBean integer, int position) {
        holder.setText(R.id.text_name,integer.getSymbol());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemListener.onClick(integer);
            }
        });
    }
}


