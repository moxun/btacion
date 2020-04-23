package com.example.myapplication.kline;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ZgwApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.kline.formatter.DateFormatter;
import com.example.myapplication.kline.units.DataHelper;
import com.example.myapplication.kline.units.KLineChartAdapter;
import com.example.myapplication.kline.units.KLineChartView;
import com.example.myapplication.kline.units.KLineEntity;
import com.example.myapplication.okhttp.OkHttpUtils;
import com.example.myapplication.okhttp.callback.ResponseCallBack;
import com.example.myapplication.okhttp.callback.ResultModelCallback;
import com.example.myapplication.utils.MoneyUtils;
import com.example.myapplication.utils.StatusBarUtil;

import com.example.myapplication.utils.StringUtil;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class KlineActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_nowPrice)
    TextView textNowPrice;
    @BindView(R.id.text_high)
    TextView textHigh;
    @BindView(R.id.text_baiFen)
    TextView textBaiFen;
    @BindView(R.id.text_cnyPrice)
    TextView textCnyPrice;
    @BindView(R.id.text_low)
    TextView textLow;
    @BindView(R.id.text_24number)
    TextView text24number;
    @BindView(R.id.text_buy)
    TextView textBuy;
    @BindView(R.id.text_sell)
    TextView textSell;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.kLineChartView)
    KLineChartView kLineChartView;
    private List datas = new ArrayList<KLineEntity>();
    private String mLegal_id;
    private String mCurrency_id;
    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private WebSocketListener mWebSocketListener;
    private WebSocket mWebSocket;
    Timer timer;
    private int intLoopCount = 0;
    private boolean isOverLoopCount = false;
    private String mName;
    private String type="1min";

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1649) {
//                mWebSocket.send("2");
            } else if (msg.what == 0x1305) {

                socket();
            }
        }
    };
    private KLineChartAdapter kLineChartAdapter;

    public void socket() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3000, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(6000, TimeUnit.SECONDS);//设置连接超时时间

        mOkHttpClient = builder.build();
        mRequest = new Request.Builder()
                .url(ZgwApplication.socket)
                .build();

        mWebSocketListener = new WebSocketListener() {
            //连接成功
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                Log.d("------","连接成功");
                mWebSocket = webSocket;
                 mWebSocket.send("{ \"sub\": \"topic to sub\", \"id\": \"id1\" }");
//                Log.d("webTest",send+"");
//                timer = new Timer();
//                TimerTask task = new TimerTask() {
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        message.what = 0x1649;
//                        handler.sendMessage(message);
//                    }
//                };
//
//                timer.schedule(task, 25000, 25000);
            }

            //服务器推送过来的消息
            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);

                //String socket = StringUtil.jianWu(text, 2);
                Log.d("k线",text);
               /* try {
                    if (socket.contains("daymarket")) {
                        String replace = socket.replace("[\"daymarket\",", "");
                        String substring = replace.substring(0, replace.length() - 1);
                        JSONObject jsonArray = new JSONObject(substring);
                        int id = jsonArray.getInt("currency_id");
                        int legal_id = jsonArray.getInt("legal_id");
                        String a = id + "";
                        String b = legal_id + "";
                        if (a.equals(mCurrency_id) && b.equals(mLegal_id)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    fuZhi(jsonArray);
                                }
                            });
                        }
                    }

                    if (socket.contains("kline")) {
                        String replace = socket.replace("[\"kline\",", "");
                        String substring = replace.substring(0, replace.length() - 1);
                        Log.d("moxun", "onMessage: " + substring);
                        JSONObject jsonArray = new JSONObject(substring);
                        int id = jsonArray.getInt("currency_id");
                        int legal_id = jsonArray.getInt("legal_id");
                        String a = id + "";
                        String b = legal_id + "";
                        if (a.equals(mCurrency_id) && b.equals(mLegal_id))
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    kline(jsonArray);
                                }
                            });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }

            //关闭连接中
            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                Log.d("-----", "onClosing: ");
                if (timer != null) {
                    timer.cancel();
                }
                intLoopCount++;
                if (intLoopCount <= 5) {
                    mOkHttpClient.newWebSocket(mRequest, mWebSocketListener);
                }
                if (intLoopCount == 6) {
                    isOverLoopCount = true;
                }
                if (isOverLoopCount) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOkHttpClient.newWebSocket(mRequest, mWebSocketListener);
                        }
                    }, 5000);
                }
            }

            //抛异常会进入其中
            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
                Log.d("-----------", "onFailure: ."+t.getMessage());
                if (t.getMessage() != null) {

                }
            }
        };

        mOkHttpClient.newWebSocket(mRequest, mWebSocketListener);
    }

    //k线数据
    private void kline(JSONObject jsonArray) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    double high = jsonArray.getDouble("high");
                    double low = jsonArray.getDouble("low");
                    double open = jsonArray.getDouble("open");
                    double close = jsonArray.getDouble("close");
                    double volume = jsonArray.getDouble("vol");
                    long time = jsonArray.getLong("time");
                    String period = jsonArray.getString("period");
                    Log.d("------------", "run: "+type);
                    if(period.equals(type)){
                        String dateToString = getDateToString(time, "mm:ss");
                        KLineEntity kLineEntity = new KLineEntity();
                        kLineEntity.setClose((float)close);
                        kLineEntity.setDate(dateToString);
                        kLineEntity.setHigh((float) high);
                        kLineEntity.setLow((float) low);
                        kLineEntity.setOpen((float) open);
                        kLineEntity.setVolume((float) volume);
                        datas.add(kLineEntity);
                        DataHelper.calculate(datas);
                        runOnUiThread((new Runnable() {
                            public final void run() {
                                kLineChartAdapter.addFooterData(datas);
                                kLineChartAdapter.notifyDataSetChanged();

                            }
                        }));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fuZhi(JSONObject jsonArray) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    textName.setText(jsonArray.getString("symbol"));
                    textHigh.setText("24H高" + MoneyUtils.decimalByUp(4, new BigDecimal(jsonArray.getInt("high"))));
                    textLow.setText("24H低" + MoneyUtils.decimalByUp(4, new BigDecimal(jsonArray.getDouble("low"))));
                    text24number.setText("24H量" + MoneyUtils.decimalByUp(4, new BigDecimal(jsonArray.getDouble("volume"))));
                    textNowPrice.setText(MoneyUtils.decimalByUp(4, new BigDecimal(jsonArray.getDouble("now_price"))).toPlainString());
                    textBaiFen.setText(jsonArray.getString("change") + "%");
                    String change = jsonArray.getString("change");
                    if (change.contains("+")) {
                        textNowPrice.setTextColor(getResources().getColor(R.color.lanse));
                        textBaiFen.setTextColor(getResources().getColor(R.color.lanse));
                    } else {
                        textNowPrice.setTextColor(getResources().getColor(R.color.chengse));
                        textBaiFen.setTextColor(getResources().getColor(R.color.chengse));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            mWebSocket.close(1000, "主动关闭连接");
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void initView() {
        chenJin();
    }

    private void chenJin() {
        StatusBarUtil.setColor(KlineActivity.this, getResources().getColor(R.color.white), 0);
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

    @Override
    protected void initData() {
        mLegal_id = getIntent().getStringExtra("Legal_id");
        mCurrency_id = getIntent().getStringExtra("Currency_id");
        mName = getIntent().getStringExtra("name");
        Message message = new Message();
        message.what = 0x1305;
        handler.sendMessage(message);

        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_zhibiao, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_fenshi, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_yifenzhong, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_wufenzhong, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_sanshifenzhong, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_yixiaoshi, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_yitian, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_yizhou, R.drawable.tab_dingdan_bg)));
        tab.addTab(tab.newTab().setCustomView(getCurrentFocus(R.string.tab_yiyue, R.drawable.tab_dingdan_bg)));

        tab.getTabAt(2).select();

        kLineChartAdapter = new KLineChartAdapter();
        kLineChartView.setAdapter(kLineChartAdapter);
        kLineChartView.setDateTimeFormatter(new DateFormatter());
        kLineChartView.setGridRows(4);
        kLineChartView.setGridColumns(4);
        long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -24);
        Date date2 = calendar.getTime();
        long time = date2.getTime() / 1000;
//        kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_1MINUTE);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabl) {
                View customView = tabl.getCustomView();
                TextView viewById = customView.findViewById(R.id.text);
                String text = viewById.getText().toString();
                if (text.equals("1分钟")) {
                    type="1min";
                    kLineChartView.setMainDrawLine(false);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR, -24);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_1MINUTE);
                } else if (text.equals("5分钟")) {
                    type="5min";
                    kLineChartView.setMainDrawLine(false);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DAY_OF_WEEK, -5);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_5MINUTE);

                } else if (text.equals("30分钟")) {
                    type="30min";
                    kLineChartView.setMainDrawLine(false);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, -1);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_30MINUTE);
                } else if (text.equals("1小时")) {
                    type="60min";
                    kLineChartView.setMainDrawLine(false);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, -2);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_1HOURS);
                } else if (text.equals("1天")) {
                    type="1day";
                    kLineChartView.setMainDrawLine(false);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -1);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_1DAY);
                } else if (text.equals("1周")) {
                    type= "1week";
                    kLineChartView.setMainDrawLine(false);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -5);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_1WEEK);

                } else if (text.equals("1月")) {
                    type="1mon";
                    kLineChartView.setMainDrawLine(false);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, -30);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_1MONTH);
                } else if (text.equals("分时")) {
                    type="--";
                    kLineChartView.setMainDrawLine(true);
                    long timeInMillis = Calendar.getInstance().getTimeInMillis() / 1000;//获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR, -24);
                    Date date2 = calendar.getTime();
                    long time = date2.getTime() / 1000;
                    kline(time + "", timeInMillis + "", mName, ZgwApplication.TYPE_1MINUTE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public View getCurrentFocus(int title, int drawable) {
        View inflate = LayoutInflater.from(KlineActivity.this).inflate(R.layout.tab_kline, null);
        TextView text = inflate.findViewById(R.id.text);
        text.setText(title);
        TextView text_bg = inflate.findViewById(R.id.text_bg);
        text_bg.setBackgroundResource(drawable);
        return inflate;
    }

    @OnClick({R.id.back, R.id.text_buy, R.id.text_sell})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
//                Intent i = new Intent(KlineActivity.this, MainActivity.class);
//                i.putExtra("tiao", "3");
//
//                startActivity(i);
                break;
            case R.id.text_buy:
                Intent intent = new Intent(KlineActivity.this, MainActivity.class);
                intent.putExtra("tiao", "1");
                intent.putExtra("name",mName);
                intent.putExtra("legal",mLegal_id);
                intent.putExtra("curr",mCurrency_id);
                startActivity(intent);

                finish();
                break;
            case R.id.text_sell:
                Intent inten1t = new Intent(KlineActivity.this, MainActivity.class);
                inten1t.putExtra("tiao", "2");
                inten1t.putExtra("name",mName);
                inten1t.putExtra("legal",mLegal_id);
                inten1t.putExtra("curr",mCurrency_id);
                startActivity(inten1t);

                finish();
                break;
        }
    }

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    private void kline(String from, String to, String symbol, String period) {

        kLineChartView.justShowLoading();
        OkHttpUtils.get()
                .url(ZgwApplication.appRequestUrl + "currency/new_timeshar?from=" + from + "&to=" + to + "&symbol=" + symbol + "&period=" + period)
                .addHeader("X-Requested-With", "XMLHttpReques")
                .build()
                .execute(new ResultModelCallback(KlineActivity.this, new ResponseCallBack<KlineBean>() {

                    @Override
                    public void onError(String e) {
                        Log.e("chimuK线数据", "onError: " + e);
                    }

                    @Override
                    public void onResponse(KlineBean response) throws JSONException {

                        datas.clear();
                        for (int i = 0; i < response.getData().size(); i++) {
                            KlineBean.DataBean dataBean = response.getData().get(i);
                            String dateToString = getDateToString(dataBean.getTime(), "mm:ss");
                            KLineEntity kLineEntity = new KLineEntity();
                            kLineEntity.setClose((float) dataBean.getClose());
                            kLineEntity.setDate(dateToString);
                            kLineEntity.setHigh((float) dataBean.getHigh());
                            kLineEntity.setLow((float) dataBean.getLow());
                            kLineEntity.setOpen((float) dataBean.getOpen());
                            kLineEntity.setVolume((float) dataBean.getVolume());
                            datas.add(kLineEntity);
                        }
                        DataHelper.calculate(datas);

                        runOnUiThread((new Runnable() {
                            public final void run() {

                                kLineChartAdapter.addFooterData(datas);
                                kLineChartAdapter.notifyDataSetChanged();
                                kLineChartView.startAnimation();
                                kLineChartView.refreshEnd();
                                kLineChartView.refreshComplete();
                            }


                        }));
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kline;
    }
}
