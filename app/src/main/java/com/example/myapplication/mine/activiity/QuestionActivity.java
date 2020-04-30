package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.WenTiBean;
import com.example.myapplication.mine.adapter.WenTiAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_question)
    RecyclerView recyQuestion;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.helpcenter));
        ArrayList<WenTiBean.DataBean.ListBean> dataBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataBeans.add(new WenTiBean.DataBean.ListBean("---","-----","-------","内容",0,"帮助信息"+i,"---"));
        }
        WenTiAdapter wenTiAdapter = new WenTiAdapter(dataBeans);
        recyQuestion.setLayoutManager(new LinearLayoutManager(this));
        recyQuestion.setAdapter(wenTiAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question;
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
