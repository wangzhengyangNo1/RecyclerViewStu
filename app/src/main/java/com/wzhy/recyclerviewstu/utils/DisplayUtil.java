package com.wzhy.recyclerviewstu.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.wzhy.recyclerviewstu.MyApp;

/**
 * Created by Administrator on 2016/3/28.
 * 屏幕适配时调用的工具内，主要负责值的转化。
 */
public class DisplayUtil {


    // 获取宽高
    public static int[] getDisplayMetricsWidthAndHeight() {
        WindowManager wm = (WindowManager) MyApp.getAppContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int API_LEVEL = android.os.Build.VERSION.SDK_INT;
        // 方法1
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (API_LEVEL >= 17) {
            display.getRealMetrics(displayMetrics);
        } else {
            display.getMetrics(displayMetrics);
        }
        int mWidth = displayMetrics.widthPixels;
        int mHeight = displayMetrics.heightPixels;

        return new int[]{mWidth, mHeight};
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param dipValue
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


}
