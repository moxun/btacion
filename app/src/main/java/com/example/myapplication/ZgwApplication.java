package com.example.myapplication;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.multidex.MultiDex;

import com.example.myapplication.bean.LogBean;
import com.example.myapplication.bean.NoticeBean;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.ForegroundCallbacks;
import com.example.myapplication.utils.SharedPreferenceUtils;
import com.example.myapplication.utils.SharedPreferencesUtils;
import com.example.myapplication.utils.ToastUtils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;



import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by LG on 2017/5/16.
 */

public class ZgwApplication extends Application {

    private static Context context;

    /**
     * baseurl地址
     */
  //  public static String appRequestUrl = "https://CoinMars.codeceo.org/api/";
    //public static String appRequestUrl = "https://www.vtsbank.com/api/";
    public static String appRequestUrl = "http://47.114.103.0:8085/";
    /**
     * 根据后期服务器图片地址修改，故用此域名访问。
     */
    public static String appPictureUrl="http://vtsbank.oss-cn-hongkong.aliyuncs.com";
    public static String socket="wss://real.okex.com:8443/ws/v3";

    public static String urlimg="http://47.114.103.0:8085/wallet/img/";
    /**
     * rjax请求的key
     */
    public static String RequestedWith="X-Requested-With";
    /**
     * rjax请求的val
     */
    public static String XmlHttpRequest="XMLHttpRequest";

    /**
     * 行情刷新时间
     */
    public static long timeInterval=3000;
    /**
     * 交易模块刷新时间
     */
    public static long timeTransaction=5000;
    /**
     * 用于判断是否打开了MainActivity
     */
    public static boolean isMainActivity=true;

    public static int RISE_COLOR = 0xFFF96A66;
    public static int FALL_COLOR = 0xFF24A272;

    public static int width;
    public static int height;

    private static Context mContext;
    private static Handler mHandler;
    private static Thread mMainThread;
    private static long		mMainThreadId;
    private static Looper mMainThreadLooper;

    public static String TYPE_1MINUTE="1min";
    public static String TYPE_5MINUTE="5min";
    public static String TYPE_30MINUTE="30min";
    public static String TYPE_1HOURS="60min";
    public static String TYPE_1DAY="1D";
    public static String TYPE_1WEEK="1W";
    public static String TYPE_1MONTH="1M";
    public static HashMap<String,String> hashMap=new HashMap<>();
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install (this);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // 程序的入口
        mContext = this;

        // handler,用来子线程和主线程通讯
        mHandler = new Handler();

        // 主线程
        mMainThread = Thread.currentThread();
        // id
        // mMainThreadId = mMainThread.getId();
        mMainThreadId = android.os.Process.myTid();

        // looper
        mMainThreadLooper = getMainLooper();

        RISE_COLOR = getResources().getColor(R.color.colorPriceRed);
        FALL_COLOR = getResources().getColor(R.color.colorPriceGreen);


        context = getApplicationContext();
        SharedPreferencesUtils.init(getApplicationContext());

        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //请求超时时间
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                //读取数据超时时间
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                //添加log插件
                //.addInterceptor(new LoggerInterceptor("TAG"))
                //持久化保持cokie
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        /**
                         * 过滤一些指定的网站，防止cookie保存时存入其他网站的cookie信息。
                         */
                        if(url.toString().indexOf(appRequestUrl)!=-1) {
                            if (cookies != null && cookies.size() > 0) {
                                for (int i = 0; i < cookies.size(); i++) {
                                    SharedPreferencesUtils.getInstance().saveData(cookies.get(i).name(), cookies.get(i).value());
//                                    Log.i(url+"---------",cookies.get(i).name()+"---------"+cookies.get(i).value());
                                }
                            }
                        }
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {

                        return new ArrayList<>();
                    }
                });


        OkHttpClient okHttpClient = builder
                .build();
        //初始化okhttp
        OkHttpUtils.initClient(okHttpClient);



        getList();

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;         // 屏幕宽度（像素）
        height = dm.heightPixels;
    }

    /**
     * 初始化应用前后台状态监听
     */
    private void initAppStatusListener() {
        ForegroundCallbacks.init(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {


            }

            @Override
            public void onBecameBackground() {

            }
        });
    }
    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static Context getContext() {
        if (context != null) {
            return context;
        } else {
            throw new NullPointerException("the context is null,please init App in Application first.");
        }

    }


    public static Handler getHandler()
    {
        return mHandler;
    }

    public static Thread getMainThread()
    {
        return mMainThread;
    }

    public static long getMainThreadId()
    {
        return mMainThreadId;
    }

    public static Looper getMainThreadLooper()
    {
        return mMainThreadLooper;
    }
    private void getList() {

        OkHttpUtils.get().url(ZgwApplication.appRequestUrl + "wallet/v1/public/log/type")

                .addHeader("X-Requested-With", "XMLHttpReques")
                .addHeader("Authorization", SharedPreferenceUtils.getToken())

                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<LogBean>() {
                    @Override
                    public void onError(String e) {
                        ToastUtils.showToast(e);
                    }

                    @Override
                    public void onResponse(LogBean response) throws JSONException {
                        hashMap.put("1",response.getData().get_$1());
                        hashMap.put("2",response.getData().get_$2());
                        hashMap.put("3",response.getData().get_$3());
                        hashMap.put("4",response.getData().get_$4());
                        hashMap.put("5",response.getData().get_$5());
                        hashMap.put("6",response.getData().get_$6());
                        hashMap.put("7",response.getData().get_$7());
                        hashMap.put("8",response.getData().get_$8());
                        hashMap.put("9",response.getData().get_$9());
                        hashMap.put("10",response.getData().get_$10());


                    }

                }));
    }

}

