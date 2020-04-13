package com.example.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**  Activity的基础类
 * Created by LG on 2017/3/7.
 */

public abstract class BaseActivity extends AppCompatActivity {


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

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(getLayoutId());
        //注解绑定
        unbinder = ButterKnife.bind(this);
        //沉浸式状态栏
        chenjin(R.color.white);
        initData();
        initView();
    }
    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
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
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
    public void chenjin(int color) {
        StatusBarUtil.setColor(BaseActivity.this, getResources().getColor(color), 0);
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


    public interface SessionInfoCallback{
        void onResponse();
    }

    public interface UserinfoCallback{
        void onResponse();
    }



    public interface PersonalInfoCallback{
        void onResponse();
    }



    public interface UserLoginCallback{
        void onResponse(String e);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    /**
//     * 处理请求返回的响应码
//     *
//     * @param resultCode
//     */
//    public void getDealMessage(String resultCode,UserLoginCallback userLoginCallback) {
//        switch (resultCode) {
//            case "-5":
//            case "-6":
//            case "2":
//            case "-2":
//            case "-3":
//                break;
//            case "-1":
//                SharedPreferencesUtils.getInstance().saveData("JSESSIONID", "");
//                SharedPreferencesUtils.getInstance().saveData("route", "");
//                SharedPreferencesUtils.getInstance().saveData("islogin",false);
//
//                UserinfoBean userinfoBean = UserInfoLiveData.getIns().getValue();
//                userinfoBean.setLogin(false);
//                userinfoBean.setSessionInfoBean(null);
//                userinfoBean.setFrontSessionBean(null);
//                UserInfoLiveData.getIns().setValue(userinfoBean);
//                Intent itLogin = new Intent(BaseActivity.this, LoginActivity.class);
//                itLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                itLogin.putExtra("type", "info");
//                startActivity(itLogin);
//                break;
//            //登陆成功
//            case "1":
//                userLoginCallback.onResponse("8989");
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
