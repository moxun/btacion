package com.example.myapplication.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;



/**
 *
 */
public class DialogUtil {


    /**
     * 在界面中间显示对话框
     */
    public static Dialog showDialogCenter(Context context, View v, int width) {
        Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(v);
        dialog.setCanceledOnTouchOutside(false);
        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
		 * 对象,这样这可以以同样的方式改变这个Activity的属性.
		 */
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        if (width != -1) {
            lp.width = DisplayUtil.dip2px(context, width);
        }

        dialogWindow.setGravity(Gravity.CENTER);

        return dialog;
    }
    /**
     * 在界面底部显示对话框
     */
    public static Dialog showDialogBottom(Context context, View v) {
        Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(v);
        dialog.setCanceledOnTouchOutside(true);

		/*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
		 * 对象,这样这可以以同样的方式改变这个Activity的属性.
		 */
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = ((Activity) context).getWindowManager().getDefaultDisplay()
                .getWidth();
        dialogWindow.setGravity(Gravity.BOTTOM);

        return dialog;
    }


    public interface ClickOptionListener{
        void clickOptionResopnse(int option);
    }
    public interface DialogClickListener {
        void dialogClick(int type);
    }

}
