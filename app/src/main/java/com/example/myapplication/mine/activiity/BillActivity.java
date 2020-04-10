package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.mine.adapter.MyBillAdapter;
import com.example.myapplication.mine.adapter.NoticeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.recy_bill)
    RecyclerView recyBill;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        chenjin(R.color.mainColor);
        recyBill.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("55"); strings.add("55"); strings.add("55"); strings.add("55"); strings.add("55");
        MyBillAdapter heyueAdapter = new MyBillAdapter(strings);
        recyBill.setLayoutManager(new LinearLayoutManager(this));
        recyBill.setAdapter(heyueAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bill;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
