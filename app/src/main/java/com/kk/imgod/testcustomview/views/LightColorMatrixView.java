package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.kk.imgod.testcustomview.R;
import com.kk.imgod.testcustomview.utils.ScreenUtils;

/**
 * 博客地址:http://blog.csdn.net/aigestudio/article/details/41212583
 */
public class LightColorMatrixView extends View {
    private Paint paint;
    private Bitmap bitmap;//将要绘制的bitmap
    private int x;//bitmap的坐标信息
    private int y;

    private boolean isClicled = false;//是否点击过

    public LightColorMatrixView(Context context) {
        this(context, null);
    }

    public LightColorMatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LightColorMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initRes();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicled = !isClicled;
                if (isClicled) {
                    paint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFFFFF00));//黄色
                } else {
                    paint.setColorFilter(null);
                }
                invalidate();
            }
        });
    }

    /**
     * 初始化资源
     */
    private void initRes() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_star);
        x = 0;
        y = 0;
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, paint);
    }
}
