package com.example.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.utils.StatusBarUtil;

import butterknife.ButterKnife;

/** Fragment的基础类
 * Created by LG on 2017/3/7.
 */

public abstract class BaseFragment extends Fragment {
    protected static Activity mActivity;
    public LayoutInflater baseInflater;

    /**
     * 初始化视图
     */
    protected  abstract void initView(View view, Bundle savedInstanceState);
    /**
     * 初始化数据
     */
    protected  abstract void initData();

    /**
     * 加载布局文件
     * @return
     */
    protected  abstract int getLayoutId();

    /**
     * 状态栏颜色
     */
    private int mStatusBarColor= R.color.white;
    /**
     * 透明度
     */
    private int mStatusBarAlpha=0;
    /**
     * 判断是否刷新
     */
    public boolean isRefreshInfo;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, inflate);
        baseInflater = LayoutInflater.from(mActivity);
        initView(inflate,savedInstanceState);
        initData();
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getActivity().getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    /**
     * 显示键盘
     */
    public void showSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getActivity().getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public int getStatusBarColor() {
        return mStatusBarColor;
    }

    public void setStatusBarColor(int statusBarColor) {
        this.mStatusBarColor = statusBarColor;
    }

    public int getStatusBarAlpha() {
        return mStatusBarAlpha;
    }

    public void setStatusBarAlpha(int statusBarAlpha) {
        this.mStatusBarAlpha = statusBarAlpha;
    }


    public interface SessionInfoCallback{
        void onResponse();
    }

    public interface UserinfoCallback{
        void onResponse();
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(mActivity, clz));
    }
    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 携带数据页面跳转并且有请求码
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public interface PersonalInfoCallback{
        void onResponse();
    }

}
