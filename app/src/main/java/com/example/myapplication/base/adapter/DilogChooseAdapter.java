package com.example.myapplication.base.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.List;

public class DilogChooseAdapter extends BaseAdapter<String> {
    private int defItem = -1;
    private OnItemListener onItemListener;
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(View v, int pos,String type);
    }
    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }

    public DilogChooseAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dialog_shoose;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String string, int position) {
        holder.setText(R.id.card_type,string);
        ImageView yes_select = holder.itemView.findViewById(R.id.choose);
        //这是第一次进来
        if (defItem != -1) {
            //第二次进来
            if (defItem == position) {
                //选中状态
                yes_select.setVisibility(View.VISIBLE);
            } else {
                yes_select.setVisibility(View.GONE);

            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(v,position,string);
            }
        });
    }
}
