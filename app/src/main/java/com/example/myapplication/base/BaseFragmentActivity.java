package com.example.myapplication.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;


import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.StatusBarUtil;

import butterknife.ButterKnife;

/**  FragmentActivity
 * Created by LG on 2017/3/7.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {

    public LayoutInflater baseInflater;

    public void chenjin(int color) {
        StatusBarUtil.setColor(this, getResources().getColor(color), 0);
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        View decor = getWindow().getDecorView();
        boolean dark = true;
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    /**
     * 初始化视图
     */
    protected  abstract void initView();

    /**
     * 初始化数据
     */
    protected  abstract void initData();

    /**
     * 加载布局文件
     * @return
     */
    protected  abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      chenjin(R.color.white);
        setContentView(getLayoutId());
        //注解绑定
        ButterKnife.bind(this);
        initData();
        baseInflater = LayoutInflater.from(this);
        initView();
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getCurrentFocus();
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
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
