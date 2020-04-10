package com.example.myapplication.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.mine.activiity.AboutusActivity;
import com.example.myapplication.mine.activiity.BillActivity;
import com.example.myapplication.mine.activiity.MyTuiGuangActivity;
import com.example.myapplication.mine.activiity.NoticeActivity;
import com.example.myapplication.mine.activiity.SeetingsActivity;


import java.util.List;

public class ResponeAdapter extends BaseAdapter<ModuleBean> {

    private final Context context;

    public ResponeAdapter(List<ModuleBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_respone;
    }

    @Override
    public void addAll(List<ModuleBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ModuleBean testBean, int position) {
        holder.setText(R.id.item_title,testBean.getTitle());
        holder.setPic(R.id.item_image_record,testBean.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(testBean.getTitle().equals("平台公告")){
                        context.startActivity(new Intent(context, NoticeActivity.class));

                    }else if(testBean.getTitle().equals("账单")){
                        context.startActivity(new Intent(context, BillActivity.class));
                    }else if(testBean.getTitle().equals("推荐返佣")){
                        context.startActivity(new Intent(context, MyTuiGuangActivity.class));
                    }else if(testBean.getTitle().equals("关于我们")){
                        context.startActivity(new Intent(context, AboutusActivity.class));
                    }else if(testBean.getTitle().equals("设置")){

                    }
                }


        });
    }
}
