package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNameActivity extends BaseActivity {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.right_button)
    TextView rightButton;
    @BindView(R.id.edit_name)
    EditText editName;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        rightButton.setVisibility(View.VISIBLE);
        toolTitle.setText(getString(R.string.update_name));
        rightButton.setText(getString(R.string.do_fwanchneg));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.finish, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.right_button:
                break;
        }
    }
}
