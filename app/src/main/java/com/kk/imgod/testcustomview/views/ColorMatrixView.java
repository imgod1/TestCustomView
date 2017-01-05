package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.utils.ScreenUtils;

/**
 * 博客地址:http://blog.csdn.net/aigestudio/article/details/41212583
 */
public class ColorMatrixView extends View {
    private Paint paint;
    private Bitmap bitmap;//将要绘制的bitmap
    private int x;//bitmap的坐标信息
    private int y;

    public ColorMatrixView(Context context) {
        this(context, null);
    }

    public ColorMatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initRes();
    }

    /**
     * 初始化资源
     */
    private void initRes() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.image09, options);//得到原图的尺寸
        int screenHeight = ScreenUtils.getScreenHeight(getContext());
        int screenWidth = ScreenUtils.getScreenWitdth(getContext());
        int scaleX = screenWidth / options.outWidth;//比较一下
        int scaleY = screenHeight / options.outHeight;
        Log.e("test", "screenHeight:" + screenHeight);
        Log.e("test", "screenWidth:" + screenWidth);
        Log.e("test", "options.outWidth:" + options.outWidth);
        Log.e("test", "options.outHeight:" + options.outHeight);
        Log.e("test", "scaleX:" + scaleX);
        Log.e("test", "scaleY:" + scaleY);
//        options.inSampleSize = scaleX < scaleY ? scaleX : scaleY;//缩放值,取小的
        options.inSampleSize = 2;//缩放值,取小的
        options.inJustDecodeBounds = false;//设置为不是只加载bound信息
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image09, options);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);


    }

    /**
     * 设置颜色过滤
     * @param colorMatrix
     */
    public void setColorFilter(ColorMatrix colorMatrix) {
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        invalidate();//重新绘制视图
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, paint);
    }
}
