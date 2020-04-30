package com.example.myapplication.mine.adapter;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.HistoryzhangBean;
import com.example.myapplication.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.List;

public class MyBillAdapter extends BaseAdapter<HistoryzhangBean.DataBean.ListBean> {
    public MyBillAdapter(List<HistoryzhangBean.DataBean.ListBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_mybill;
    }

    @Override
    public void addAll(List<HistoryzhangBean.DataBean.ListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, HistoryzhangBean.DataBean.ListBean string, int position) {
        holder.setText(R.id.item_notice_time,string.getAddTime());
        holder.setText(R.id.item_title, ZgwApplication.hashMap.get(string.getType()+""));
        holder.setText(R.id.item_money, MoneyUtils.decimalByUp(2, new BigDecimal(string.getAmount()))+"");
    }
}
