package com.kk.imgod.testcustomview.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 项目名称：TestCustomView
 * 类描述：屏幕相关的工具类
 * 创建人：gk
 * 创建时间：2017/1/4 17:23
 * 修改人：gk
 * 修改时间：2017/1/4 17:23
 * 修改备注：
 */
public class ScreenUtils {

    /**
     * 获取屏幕的宽度(单位像素)
     *
     * @param context
     * @return
     */
    public static int getScreenWitdth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕的高度(单位像素)
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * 获取到DisplayMetrics信息
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getDefaultDisplay(context).getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * 获取默认的Display
     *
     * @param context
     * @return
     */
    public static Display getDefaultDisplay(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay();
    }
}
