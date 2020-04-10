package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


public class DisplayUtil {
	public static float getDensity(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.density;
	}

	public static int getDensityWdith(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getDensityHeight(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.heightPixels;
	}

	// 1.代码中设置setXXSize的都是px单位，都需要把布局中的dp，sp转成px才能使用
	/**
	 * 根据手机分辨率从 px(像素) 单位 转成 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机分辨率从 dp 单位 转成 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	public static int dp2px(Context context, float dp) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (dp * density + 0.5f);
	}

	public static int px2dp(Context context, int px) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (px / density + 0.5f);
	}
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int getWidth(Context context, int liangbianjuli, int num,
                               int picjuli) {
		// 屏幕宽度-两边的距离-图片中间的距离 除以列数
		return (getDensityWdith(context)
				- DisplayUtil.dip2px(context, liangbianjuli) * 2 - dip2px(
				context, picjuli) * (num - 1))
				/ num;
	}
	public static int getWidth(Context context, int percent) {
		// 获取屏幕的高度
		return Integer.parseInt((getDensityWdith(context)*percent)/100+"");
	}
	public static int getHeight(Context context, int fenmu) {
		// 获取屏幕的高度
		return (getDensityHeight(context) / fenmu);
	}

	public static void backgroundAlpha(Context context, float bgAlpha) {
		WindowManager.LayoutParams lp = ((Activity) context).getWindow()
				.getAttributes();
		//dialog 放开注释
//		((Activity) context).getWindow().addFlags(
//				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		lp.alpha = bgAlpha; // 0.0-1.0
		((Activity) context).getWindow().setAttributes(lp);
	}


	/**
	 * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
	 * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
	 * @param anchorView  呼出window的view
	 * @param contentView   window的内容布局
	 * @return window显示的左上角的xOff,yOff坐标
	 */
	public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
		final int windowPos[] = new int[2];
		final int anchorLoc[] = new int[2];
		// 获取锚点View在屏幕上的左上角坐标位置
		anchorView.getLocationOnScreen(anchorLoc);
		final int anchorHeight = anchorView.getHeight();
		// 获取屏幕的高宽
		final int screenHeight = DisplayUtil.getDensityHeight(anchorView.getContext());
		final int screenWidth = DisplayUtil.getDensityWdith(anchorView.getContext());
		contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		// 计算contentView的高宽
		final int windowHeight = contentView.getMeasuredHeight();
		final int windowWidth = contentView.getMeasuredWidth();
		// 判断需要向上弹出还是向下弹出显示
		final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
		if (isNeedShowUp) {
			windowPos[0] = screenWidth - windowWidth;
			windowPos[1] = anchorLoc[1] - windowHeight;
		} else {
			windowPos[0] = screenWidth - windowWidth;
			windowPos[1] = anchorLoc[1] + anchorHeight;
		}
		return windowPos;
	}

	/**
	 * 获得状态栏的高度
	 *
	 * @param context
	 * @return mStatusHeight
	 */
	public static int getStatusHeight(Context context) {
		if (mStatusHeight != -1) {
			return mStatusHeight;
		}
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
			return 0;
		}
		try {
			int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
			if (resourceId > 0) {
				mStatusHeight = context.getResources().getDimensionPixelSize(resourceId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mStatusHeight;
	}

	// 状态栏高度
	private static int mStatusHeight = -1;
}
