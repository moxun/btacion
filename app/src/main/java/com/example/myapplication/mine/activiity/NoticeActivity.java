package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.mine.adapter.HeyueAdapter;
import com.example.myapplication.mine.adapter.NoticeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_notic)
    RecyclerView recyNotic;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.xinwen_notice));
        recyNotic.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("55"); strings.add("55"); strings.add("55"); strings.add("55"); strings.add("55");
        NoticeAdapter heyueAdapter = new NoticeAdapter(strings,this);
        recyNotic.setLayoutManager(new LinearLayoutManager(this));
        recyNotic.setAdapter(heyueAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
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
