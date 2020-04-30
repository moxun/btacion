package com.example.myapplication.heyue.adapter;

import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.AmountsBean;
import com.example.myapplication.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.List;

public class AmountsAdapter extends BaseAdapter<AmountsBean.DataBean> {

    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick( AmountsBean.DataBean dataBean, String s);
    }
    public AmountsAdapter(List<AmountsBean.DataBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_amounts;
    }

    @Override
    public void addAll(List<AmountsBean.DataBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, AmountsBean.DataBean dataBean, int position) {
        String s = MoneyUtils.decimalByUp(0, new BigDecimal(dataBean.getAmount())).toString();
        holder.setText(R.id.geshu,s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemListener.onClick(dataBean,s);
            }
        });
    }
}
