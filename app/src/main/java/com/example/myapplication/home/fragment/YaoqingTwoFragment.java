package com.example.myapplication.home.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.utils.Qrutils;
import com.example.myapplication.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.myapplication.utils.ToastUtils.showToast;

public class YaoqingTwoFragment extends BaseFragment {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.erweima)
    ImageView erweima;
    @BindView(R.id.my_coede)
    TextView myCoede;
    @BindView(R.id.copy_code)
    TextView copyCode;
    private String code;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        code = arguments.getString("code");
        myCoede.setText(code);
        Bitmap bitmap = Qrutils.generateBitmap(code, 1000, 1000);
        erweima.setImageBitmap(bitmap);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yaoqing_two;
    }

    @OnClick({R.id.finish, R.id.copy_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                mActivity.finish();
                break;
            case R.id.copy_code:
                 ClipboardManager cm;
                 ClipData mClipData;
//获取剪贴板管理器：
                cm = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
                mClipData = ClipData.newPlainText("Label", code);
// 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                ToastUtils.showToast(getString(R.string.copy_success));

                break;
        }
    }
}
