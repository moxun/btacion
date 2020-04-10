package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.hangqing.fragment.HangqingFragment;
import com.example.myapplication.heyue.fragment.HeyueFragment;
import com.example.myapplication.home.fragment.HomeFragment;
import com.example.myapplication.mine.fragment.MineFragment;
import com.example.myapplication.utils.StatusBarUtil;
import com.example.myapplication.utils.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.content_layout)
    FrameLayout contentLayout;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private HomeFragment homePagerFragment;
    private HangqingFragment hangqingFragment;
    private MineFragment mineFragment;
    private HeyueFragment heyueFragment;
    private int mIndex = 0;
    private boolean mIsExit;
    String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void initView() {

    }

    //获取权限
    @SuppressLint("WrongConstant")
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            //已经打开权限

        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的使用权限", 1, perms);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void initData() {
        homePagerFragment = new HomeFragment();
        hangqingFragment = new HangqingFragment();
        heyueFragment = new HeyueFragment();
        mineFragment = new MineFragment();
        mFragments.add(homePagerFragment);
        mFragments.add(hangqingFragment);
        mFragments.add(heyueFragment);
        mFragments.add(mineFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content_layout, homePagerFragment).commit();
        setButtomNavigationView();
        getPermission();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    private void setButtomNavigationView() {

        adjustNavigationIcoSize(bottomNavigationView);
        //取消默认的导航栏颜色
//       bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragment(ft);
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        setIndexSelected(0);
                        chenjin(R.color.white);
//                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
//                        }
                        break;
                    case R.id.tab_classfiy:
                        setIndexSelected(1);
//                        ChangColor(R.color.mainColor);
                        chenjin(R.color.white);
                        break;
                    case R.id.tab_tiku:
//                        ChangColor(R.color.white);
                        setIndexSelected(2);
                        chenjin(R.color.white);
                        break;
                    case R.id.tab_mine:
                        chenjin(R.color.mainColor);
                        setIndexSelected(3);

                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    private void setIndexSelected(int index) {

        if (mIndex == index) {
            return;
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments.get(mIndex));
        //判断是否添加
        if (!mFragments.get(index).isAdded()) {
            ft.add(R.id.content_layout, mFragments.get(index)).show(mFragments.get(index));
        } else {
            ft.show(mFragments.get(index));
        }

        ft.commit();
        //再次赋值
        mIndex = index;


    }


    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homePagerFragment != null) {
            transaction.hide(homePagerFragment);
        }
        if (hangqingFragment != null) {
            transaction.hide(hangqingFragment);
        }
        if (heyueFragment != null) {
            transaction.hide(heyueFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    @Override
    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();

            } else {
                Toast.makeText(this, getString(R.string.again_out), Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void adjustNavigationIcoSize(BottomNavigationView navigation) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(R.id.icon);
            View viewById = menuView.getChildAt(i).findViewById(R.id.text);

            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, displayMetrics);


            iconView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
