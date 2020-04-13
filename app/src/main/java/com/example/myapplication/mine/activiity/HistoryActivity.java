package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.mine.adapter.HistoryOrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_history)
    RecyclerView recyHistory;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.history_order));
        ArrayList<String> historyorders = new ArrayList<>();
        historyorders.add("55"); historyorders.add("55"); historyorders.add("55"); historyorders.add("55"); historyorders.add("55");
        HistoryOrderAdapter historyOrderAdapter = new HistoryOrderAdapter(historyorders);
        recyHistory.setLayoutManager(new LinearLayoutManager(this));
        recyHistory.setAdapter(historyOrderAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
    }
}
