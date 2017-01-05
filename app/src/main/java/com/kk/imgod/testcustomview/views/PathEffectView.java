package com.kk.imgod.testcustomview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 博客地址:http://blog.csdn.net/aigestudio/article/details/41447349
 */
public class PathEffectView extends View {
    private Paint paint;
    private Path mPath;
    private int mPhase;
    private PathEffect[] pathEffect;

    public PathEffectView(Context context) {
        this(context, null);
    }

    public PathEffectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPath();
        initRes();
    }

    private void initPath() {
        mPath = new Path();
        // 定义路径的起点
        mPath.moveTo(0, 0);

        // 定义路径的各个点
        for (int i = 0; i <= 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }
        pathEffect = new PathEffect[7];
    }

    /**
     * 初始化资源
     */
    private void initRes() {
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
         * 实例化各类特效
         */
        pathEffect[0] = null;
        pathEffect[1] = new CornerPathEffect(10);//转折处圆滑
        pathEffect[2] = new DiscretePathEffect(3.0F, 5.0F);//铁丝生锈效果
        pathEffect[3] = new DashPathEffect(new float[]{30, 15}, mPhase);//数组里面的参数是说线段的长短,数组里面有2个参数即可
        Path tempPath = new Path();
        tempPath.addRect(0, 0, 8, 8, Path.Direction.CCW);
        pathEffect[4] = new PathDashPathEffect(tempPath, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        pathEffect[5] = new ComposePathEffect(pathEffect[2], pathEffect[4]);
        pathEffect[6] = new SumPathEffect(pathEffect[4], pathEffect[3]);

        /*
         * 绘制路径
         */
        for (int i = 0; i < pathEffect.length; i++) {
            paint.setPathEffect(pathEffect[i]);
            canvas.drawPath(mPath, paint);

            // 每绘制一条将画布向下平移250个像素
            canvas.translate(0, 250);
            // 刷新偏移值并重绘视图实现动画效果
        }
        // 刷新偏移值并重绘视图实现动画效果
//        mPhase += 1;
//        invalidate();
    }
}
