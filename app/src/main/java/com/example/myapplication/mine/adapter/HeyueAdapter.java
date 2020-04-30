package com.example.myapplication.mine.adapter;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.UserinfoBean;
import com.example.myapplication.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.List;

public class HeyueAdapter  extends BaseAdapter<UserinfoBean.DataBean.BalanceModelsBean> {
    public HeyueAdapter(List<UserinfoBean.DataBean.BalanceModelsBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_heyue;
    }

    @Override
    public void addAll(List<UserinfoBean.DataBean.BalanceModelsBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, UserinfoBean.DataBean.BalanceModelsBean string, int position) {
        holder.setPic(R.id.img_bitype, ZgwApplication.urlimg+string.getCoin().getCoinImg());
        holder.setText(R.id.bi_name,string.getCoin().getCoinName());
        holder.setText(R.id.zonge, MoneyUtils.decimalByUp(2, new BigDecimal(string.getAmount()))+"");
        holder.setText(R.id.keyong,MoneyUtils.decimalByUp(2, new BigDecimal(string.getAmount()-string.getFreeze()))+"");

    }
}
