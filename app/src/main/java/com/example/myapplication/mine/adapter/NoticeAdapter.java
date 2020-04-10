package com.example.myapplication.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.mine.activiity.NoticeActivity;
import com.example.myapplication.mine.activiity.NoticeInfoActivity;

import java.util.List;

public class NoticeAdapter extends BaseAdapter<String> {

    private final Context context;

    public NoticeAdapter(List<String> dataList, Context noticeActivity) {
        super(dataList);
        context =noticeActivity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_notice;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String string, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NoticeInfoActivity.class));
            }
        });
    }
}
