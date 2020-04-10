package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TibiActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.choose_bi)
    TextView chooseBi;
    @BindView(R.id.saoyisao)
    ImageView saoyisao;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.keyong)
    TextView keyong;
    @BindView(R.id.ketong_size)
    TextView ketongSize;
    @BindView(R.id.dongjie_size)
    TextView dongjieSize;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.chongbi_hint)
    TextView chongbiHint;
    @BindView(R.id.next_do)
    TextView nextDo;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.tibi));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tibi;
    }




    @OnClick({R.id.finish, R.id.choose_bi, R.id.saoyisao, R.id.next_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.choose_bi:
                break;
            case R.id.saoyisao:
                break;
            case R.id.next_do:
                break;
        }
    }
}
