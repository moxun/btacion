package com.example.myapplication.home.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.utils.Qrutils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class YaoqingOneFragment extends BaseFragment {


    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.youcode)
    TextView youcode;
    @BindView(R.id.erweima)
    ImageView erweima;

    public YaoqingOneFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String code = arguments.getString("code");
        youcode.setText(getString(R.string.youcode)+code);
        Bitmap bitmap = Qrutils.generateBitmap(code, 1000, 1000);
        erweima.setImageBitmap(bitmap);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yaoqing_one;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        mActivity.finish();
    }
}
