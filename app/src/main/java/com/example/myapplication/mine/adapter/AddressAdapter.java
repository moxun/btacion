package com.example.myapplication.mine.adapter;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.adapter.BaseAdapter;
import com.example.myapplication.bean.AddressBean;
import com.example.myapplication.bean.UserinfoBean;

import java.util.List;

public class AddressAdapter extends BaseAdapter<AddressBean.DataBean> {
    public AddressAdapter(List<AddressBean.DataBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tibiaddress;
    }

    @Override
    public void addAll(List<AddressBean.DataBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, AddressBean.DataBean rechargeAddressBeanX, int position) {
        holder.setPic(R.id.item_tibi_img, ZgwApplication.urlimg+rechargeAddressBeanX.getCoin().getCoinImg());
        holder.setText(R.id.item_title,rechargeAddressBeanX.getCoin().getCoinName());

    }
}
