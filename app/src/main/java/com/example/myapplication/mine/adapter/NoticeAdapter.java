package com.example.myapplication.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.NoticeBean;
import com.example.myapplication.mine.activiity.NoticeActivity;
import com.example.myapplication.mine.activiity.NoticeInfoActivity;

import java.util.List;

public class NoticeAdapter extends BaseAdapter<NoticeBean.DataBean.ListBean> {

    private final Context context;

    public NoticeAdapter(List<NoticeBean.DataBean.ListBean> dataList, Context noticeActivity) {
        super(dataList);
        context =noticeActivity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_notice;
    }

    @Override
    public void addAll(List<NoticeBean.DataBean.ListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, NoticeBean.DataBean.ListBean string, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NoticeInfoActivity.class).putExtra("id",string.getId()));
            }
        });
        holder.setText(R.id.item_title,string.getTitle());
        holder.setText(R.id.item_notice_time,string.getAddTime());
    }
}
