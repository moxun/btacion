package com.example.myapplication.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.myapplication.Login.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.heyue.activiity.HuazhuanActivity;
import com.example.myapplication.heyue.activiity.InvitationActivity;
import com.example.myapplication.heyue.activiity.SySuccessActivity;
import com.example.myapplication.kline.KlineActivity;
import com.example.myapplication.mine.activiity.AddressActivity;
import com.example.myapplication.mine.activiity.ChongbiActivity;
import com.example.myapplication.mine.activiity.HistoryActivity;
import com.example.myapplication.mine.activiity.MyzichanActivity;
import com.example.myapplication.mine.activiity.TibiActivity;

import java.util.List;

public class HomeModleAdapter extends BaseAdapter<ModuleBean> {

    private final Context context;

    public HomeModleAdapter(List<ModuleBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_modle;
    }

    @Override
    public void addAll(List<ModuleBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ModuleBean moduleBean, int position) {
        holder.setPic(R.id.ite_module_src,moduleBean.getImage());
        holder.setText(R.id.ite_module_title,moduleBean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(moduleBean.getTitle().equals("在线客服")){

                }else if(moduleBean.getTitle().equals("充币")){
                    context.startActivity(new Intent(context, ChongbiActivity.class));
                }else if(moduleBean.getTitle().equals("提币")){
                    context.startActivity(new Intent(context, TibiActivity.class));
                }else if(moduleBean.getTitle().equals("帮助中心")){

                }
            }


        });
    }
}