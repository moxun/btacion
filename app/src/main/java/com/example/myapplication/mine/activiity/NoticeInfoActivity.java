package com.example.myapplication.mine.activiity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.bean.NoticeBean;
import com.example.myapplication.bean.NoticeinfoBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.ToastUtils;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeInfoActivity extends BaseActivity {

    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;

    @Override
    protected void initView() {
        getinFo(getIntent().getIntExtra("id", 0));
    }

    private void getinFo(int id) {
        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/user/notice/detail?id=" + id)

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())

                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<NoticeinfoBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(NoticeinfoBean response) throws JSONException {
                        title.setText(response.getData().getTitle());
                        time.setText(response.getData().getAddTime());
                        content.setText(response.getData().getContentText());
                    }
                }));
    }

    @Override
    protected void initData() {
        toolTitle.setText(getString(R.string.notice_info));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_info;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
