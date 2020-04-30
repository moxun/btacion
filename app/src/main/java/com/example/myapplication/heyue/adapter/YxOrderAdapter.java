package com.example.myapplication.heyue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.HistpryOrderBean;
import com.example.myapplication.bean.OrderBean;
import com.example.myapplication.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.List;

public class YxOrderAdapter  extends BaseAdapter<HistpryOrderBean.DataBean.ListBean> {

    private final Context context;

    public YxOrderAdapter(List<HistpryOrderBean.DataBean.ListBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_histry;
    }

    @Override
    public void addAll(List<HistpryOrderBean.DataBean.ListBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, HistpryOrderBean.DataBean.ListBean string, int position) {

        holder.setText(R.id.lever,"×"+string.getLever());
        holder.setText(R.id.createTime,context.getString(R.string.katime)+string.getCreateTime());
        holder.setText(R.id.canleTime,context.getString(R.string.close_time)+string.getCancelTime());
        holder.setText(R.id.create_price,string.getPrice()+"");
//        holder.setText(R.id.benjin,string.getAmount()+"");
        TextView viewById = holder.itemView.findViewById(R.id.yingkui);

//        double d = ((string.getOpenPrice() - string.getClosePrice()) * Math.abs(string.getMargin()) * string.getLeverage()) - string.getFee();
//        BigDecimal bigDecimal = MoneyUtils.decimalByUp(2, new BigDecimal(d));
//        holder.setText(R.id.yingkui,bigDecimal+"");
//        if(d>0)
//        {
//            viewById.setTextColor(Color.parseColor("#03AD90"));
//        }else {
//            viewById.setTextColor(Color.parseColor("#F65448"));
//        }

        holder.setText(R.id.fee,string.getFee()+"");
        if(string.getAmount()>0){
            holder.setText(R.id.text_1,"多");
        }else {
            holder.setText(R.id.text_1,"空");
        }
        holder.setText(R.id.benjin,string.getAmount()+"");
//        if(string.getSide().equals("perpetual")){
//            holder.setText(R.id.type,"永续合约");
//        }else {


    }
}
