package com.example.myapplication.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.ModuleBean;
import com.example.myapplication.bean.TickerinfoBean;
import com.example.myapplication.kline.KlineActivity;
import com.example.myapplication.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.List;

public class HomeHeyueAdapter extends BaseAdapter<TickerinfoBean.DataBean> {

    private final Context context;

    public HomeHeyueAdapter(List<TickerinfoBean.DataBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_heyue;
    }

    @Override
    public void addAll(List<TickerinfoBean.DataBean> list, int page) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void createHolder(ViewHolder holder, TickerinfoBean.DataBean string, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, KlineActivity.class));
            }
        });

        holder.setText(R.id.text_1_bi,string.getInstrumentId());
        holder.setText(R.id.text_3_bi, "24H量  "+MoneyUtils.decimalByUp(2, new BigDecimal(string.getBaseVolume24H()))+"");
        holder.setText(R.id.text_4_bi,MoneyUtils.decimalByUp(2, new BigDecimal(string.getLast()))+"");
        holder.setText(R.id.text_5_bi,"≈￥"+MoneyUtils.decimalByUp(2, new BigDecimal(string.getLast())).multiply(BigDecimal.valueOf(7))+"");

        float yk=(string.getLast()-string.getOpen24H())/string.getOpen24H();
        TextView tee = holder.itemView.findViewById(R.id.yk);

        if(yk>=0){
            tee.setBackgroundResource(R.drawable.lanse_bg_3);
            tee.setText("+"+MoneyUtils.decimalByUp(2, new BigDecimal(yk))+"%");
        }else {
            tee.setBackgroundResource(R.drawable.red_radio3);
            tee.setText("-"+MoneyUtils.decimalByUp(2, new BigDecimal(yk))+"%");
        }


    }
}
