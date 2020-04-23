package com.example.myapplication.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/20.
 */
public class StringUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
//
    //^[a-zA-Z0-9_]+$

    /**
     * 判断是否是手机号
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        boolean isValid = false;
        String expression = "1((((3[0-3,5-9])|(4[5,7,9])|(5[0-3,5-9])|(66)|(7[1-3,5-8])|(8[0-9])|(9[1,8,9]))[0-9]{8})|((34)[0-8]\\d{7}))";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 是否包含特殊字符
     * @return
     */
    public static boolean isContaintSymbol(String content) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        if (m.find()) {
            return true;
        }
        return false;
    }
    public static String notEmpty(String value) {
        if (value != null && !TextUtils.isEmpty(value) && !TextUtils.isEmpty(value.trim())) {
            return value;
        }
        return "N/A";
    }
    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isValidNumeric(String str) {
        try {
            Float.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /***
     * 将输入金额num转换为汉字大写格式
     *
     * @param number 输入金额（小于10000000）
     * @return 金额的大写格式
     */
    public static String transferPriceToHanzi(String number) {
        BigDecimal num;
        if (TextUtils.isEmpty(number.trim())) {
            return "零";
        } else if (number.startsWith("-")) {
            return "输入金额不能为负数";
        } else {
            num = new BigDecimal(number.trim());
        }

        String[] upChinese = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌",
                "玖",};
        String[] upChinese2 = {"分", "角", "圆", "拾", "佰", "仟", "萬", "拾", "佰",
                "仟", "亿", "拾", "佰", "仟", "兆"};
        StringBuffer result = new StringBuffer();
        int count = 0;
        int zeroflag = 0;
        boolean mantissa = false;
        if (num.compareTo(BigDecimal.ZERO) < 0) {
            // 输入值小于零
            return "输入金额不能为负数";
        }
        if (num.compareTo(BigDecimal.ZERO) == 0) {
            // 输入值等于零
            return "零";
        }
        if (String.valueOf(num).length() > 12) { // 输入值过大转为科学计数法本方法无法转换
            return "您输入的金额过大";
        }
        BigDecimal temp = num.multiply(BigDecimal.TEN.pow(2));
        BigDecimal[] divideAndRemainder = temp
                .divideAndRemainder(BigDecimal.TEN.pow(2));
        if (divideAndRemainder[1].compareTo(BigDecimal.ZERO) == 0) {
            // 金额为整时
            if (temp.compareTo(BigDecimal.ZERO) == 0)
                return "您输入的金额过小";
            // 输入额为e:0.0012小于分计量单位时
            result.insert(0, "整");
            temp = temp.divide(BigDecimal.TEN.pow(2));
            count = 2;
            mantissa = true;
        }
        while (temp.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal[] divideAndRemainder2 = temp
                    .divideAndRemainder(BigDecimal.TEN);
            BigDecimal t = divideAndRemainder2[1];
            // 取得最后一位
            if (t.compareTo(BigDecimal.ZERO) != 0) {
                // 最后一位不为零时
                if (zeroflag >= 1) {
                    // 对该位前的单个或多个零位进行处理
                    if (((!mantissa) && count == 1)) {
                        // 不是整数金额且分为为零
                    } else if (count > 2 && count - zeroflag < 2) {
                        // 输入金额为400.04小数点前后都有零

                        result.insert(1, "零");
                    } else if (count > 6 && count - zeroflag < 6 && count < 10) {
                        // 万位后为零且万位为零
                        if (count - zeroflag == 2) {
                            // 输入值如400000
                            result.insert(0, "萬");
                        } else {
                            result.insert(0, "萬零");
                            // 输入值如400101
                        }
                    } else if (count > 10 && count - zeroflag < 10) {
                        if (count - zeroflag == 2) {
                            result.insert(0, "亿");
                        } else {
                            result.insert(0, "亿零");
                        }
                    } else if (((count - zeroflag) == 2)) {
                        // 个位为零
                    } else if (count > 6 && count - zeroflag == 6 && count < 10) { // 以万位开始出现零如4001000
                        result.insert(0, "萬");
                    } else if (count == 11 && zeroflag == 1) {
                        result.insert(0, "亿");
                    } else {
                        result.insert(0, "零");
                    }
                }
                result.insert(0, upChinese[t.intValue()] + upChinese2[count]);
                zeroflag = 0;
            } else {
                if (count == 2) {
                    result.insert(0, upChinese2[count]);
                    // 个位为零补上"圆"字
                }
                zeroflag++;
            }
            BigDecimal[] divideAndRemainder3 = temp
                    .divideAndRemainder(BigDecimal.TEN);
            temp = divideAndRemainder3[0];
            System.out.println("count=" + count + "---zero=" + zeroflag
                    + "----" + result.toString());
            count++;
            if (count > 14)
                break;
        }
        return result.toString();
    }

    static String[] units = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿",
            "十亿", "百亿", "千亿", "万亿"};
    public static char[] numArray = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};

    /**
     * 大写数字转换成小写数字
     *      * @param num
     *      * @return
     */
    public static String formatInteger(String num) {

        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        if (!"0".equals(num)) {
            for (int i = 0; i < len; i++) {
                String m = val[i] + "";
                int n = Integer.valueOf(m);
                boolean isZero = n == 0;
                String unit = units[(len - 1) - i];
                if (isZero) {
                    if ('0' == val[i - 1]) {
                        continue;
                    } else {
                        sb.append(numArray[n]);
                    }
                } else {
                    sb.append(numArray[n]);
                    sb.append(unit);
                }
            }
        } else {
            sb.append("零");
        }

        return sb.toString();
    }



    /**
     * 设置TextView显示文本长度
     * @param tv
     * @param length
     */
    public static void setMaxLenght(TextView tv, int length) {
        tv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    public static String getSex(String sex) {
        String rid = "1";
        switch (sex) {
            case "男":
                rid = "1";
                break;
            case "女":
                rid = "2";
                break;
            default:
                rid = "";
                break;
        }
        return rid + "";
    }

    /**
     * 判断是否是邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        if (isContainChinese(email)) {
            return false;
        }
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是否是邮箱
     * @param email
     * @return
     */
    public static boolean checkEmailPhone(String email) {
        boolean isCheck = false;
        if (!isPhoneNumber(email)) {
            isCheck = false;
        } else {
            return true;
        }
        if (email == null || email.trim().length() == 0)
            return false;
        if (isContainChinese(email)) {
            return false;
        }
        if (emailer.matcher(email).matches()) {
            return true;
        }
        return isCheck;
    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * @return
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    public static boolean isHasHttp(String httpurl) {
        if (!TextUtils.isEmpty(httpurl)) {
            return httpurl.contains("http://");
        } else
            return false;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    public static String getString(String s) {
        return s == null ? "" : s;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line + "<br>");
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /***
     * 截取字符串
     *
     * @param start 从那里开始，0算起
     * @param num   截取多少个
     * @param str   截取的字符串
     * @return
     */
    public static String getSubString(int start, int num, String str) {
        if (str == null) {
            return "";
        }
        int leng = str.length();
        if (start < 0) {
            start = 0;
        }
        if (start > leng) {
            start = leng;
        }
        if (num < 0) {
            num = 1;
        }
        int end = start + num;
        if (end > leng) {
            end = leng;
        }
        return str.substring(start, end);
    }


    /**
     * 隐藏软键盘
     * @param context
     */
    public static void hideSoftKeyboard(Activity context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (context.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (context.getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(context
                                .getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示虚拟键盘
     * @param v
     */
    public static void showKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }


    /**
     * sim卡是否可读
     * @param context
     * @return
     */
    public static boolean isCanUseSim(Context context) {
        try {
            TelephonyManager mgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            return TelephonyManager.SIM_STATE_READY == mgr.getSimState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * TextView左侧嵌入图标
     * @param context
     * @param view
     * @param rsd
     */
    public static void setDrawbleLeft(Context context, TextView view, int rsd) {
        Drawable drawable = ContextCompat.getDrawable(context, rsd);
        // / 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable, null, null, null);
    }
    /**
     * TextView右侧嵌入图标
     * @param context
     * @param view
     * @param rsd
     */
    public static void setDrawbleRight(Context context, TextView view, int rsd) {
        Drawable drawable = context.getResources().getDrawable(rsd);
        // / 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        view.setCompoundDrawables(null, null, drawable, null);
    }
    /**
     * TextView頂部嵌入图标
     * @param context
     * @param view
     * @param rsd
     */
    public static void setDrawbleTop(Context context, TextView view, int rsd) {
        Drawable drawable = context.getResources().getDrawable(rsd);
        // / 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        view.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * TextView显示html格式数据
     * @param tv
     * @param rsd
     */
    public static void setText(TextView tv, String rsd) {
        tv.setText(Html.fromHtml(rsd));
    }
    /**
     * 字符串转换成double
     * @param number
     * @return
     */
    public static double strToDouble(String number) {
        double n = 0l;
        try {
            n = Double.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }
    /**
     * 字符串转换成int
     * @param number
     * @return
     */
    public static int strToInteger(String number) {
        int n = 0;
        try {
            n = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 字符串转换成long
     * @param number
     * @return
     */
    public static long strToLong(String number) {
        long n = 0;
        try {
            n = Long.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 获取当前屏幕旋转角度
     *
     * @param context
     * @return 0表示是竖屏; 90表示是左横屏; 180表示是反向竖屏; 270表示是右横屏
     */
    public static int getScreenRotationOnPhone(Context context) {
        final Display display = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return -90;
        }
        return 0;
    }
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 功能：身份证的有效验证
     *
     * @param IDStr
     *            身份证号
     * @return 有效：返回"" 无效：返回String信息
     * @throws ParseException
     */
    @SuppressLint("WrongConstant")
    public static boolean idCardValidate(String IDStr){
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return false;
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return false;
            }
        } else {
            return true;
        }
        // =====================(end)=====================
        return true;
    }
    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    private static boolean isDataFormat(String str) {
        boolean flag = false;
        // String
        // regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }
    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    public static void setTextWatcher(final EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && "0".equals(s.toString().substring(0, 1))) {
                    et.setText(s.subSequence(1, s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
