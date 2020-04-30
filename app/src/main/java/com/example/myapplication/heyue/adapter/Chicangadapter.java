package com.example.myapplication.heyue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.OrderBean;
import com.example.myapplication.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.List;

public class Chicangadapter extends BaseAdapter<OrderBean.DataBean> {

    private final Context context;
    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(OrderBean.DataBean string );
        void setClose(OrderBean.DataBean string);
    }
    public Chicangadapter(List<OrderBean.DataBean> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_chicang;
    }

    @Override
    public void addAll(List<OrderBean.DataBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, OrderBean.DataBean string, int position) {

        holder.setText(R.id.lever, "×" + string.getLeverage());
        holder.setText(R.id.createTime, context.getString(R.string.katime) + string.getCreateTime());
        holder.setText(R.id.canleTime, context.getString(R.string.close_time) + string.getCloseTime());
        holder.setText(R.id.create_price, MoneyUtils.decimalByUp(2, new BigDecimal(string.getOpenPrice())) + "");
//        holder.setText(R.id.benjin,string.getAmount()+"");
        TextView viewById = holder.itemView.findViewById(R.id.yingkui);

        double d = ((string.getOpenPrice() - string.getClosePrice()) * Math.abs(string.getMargin()) * string.getLeverage()) - string.getFee();
        BigDecimal bigDecimal = MoneyUtils.decimalByUp(2, new BigDecimal(d));
        holder.setText(R.id.yingkui, bigDecimal + "");
        if (d > 0) {
            viewById.setTextColor(Color.parseColor("#03AD90"));
        } else {
            viewById.setTextColor(Color.parseColor("#F65448"));
        }
        holder.setText(R.id.end_price, MoneyUtils.decimalByUp(2, new BigDecimal(string.getStopProfit()))+ "");
        holder.setText(R.id.fee, MoneyUtils.decimalByUp(2, new BigDecimal(string.getFee()))+ "");
        if (string.getMargin() > 0) {
            holder.setText(R.id.text_1, "多");
        } else {
            holder.setText(R.id.text_1, "空");
        }
        holder.setText(R.id.benjin, MoneyUtils.decimalByUp(2, new BigDecimal(string.getMargin())) + "");
//        if(string.getSide().equals("perpetual")){
//            holder.setText(R.id.type,"永续合约");
//        }else {
        holder.setText(R.id.type, MoneyUtils.decimalByUp(2, new BigDecimal(string.getStopLoss()))+"");
        TextView pingcang = holder.itemView.findViewById(R.id.pingcang);
        TextView yksz = holder.itemView.findViewById(R.id.yksz);
        pingcang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(string);
            }
        });
        yksz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.setClose(string);
            }
        });
    }
}


